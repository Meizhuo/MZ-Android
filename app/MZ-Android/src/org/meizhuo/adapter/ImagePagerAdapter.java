package org.meizhuo.adapter;

import java.util.List;

import org.meizhuo.app.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.jakewharton.salvage.RecyclingPagerAdapter;

/**
 * 图片轮播(默认无限)
 * 
 * @author Trinea
 * @author Jayin
 * 
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

	private Context context;
	private List<Integer> imageIdList;
	private List<String>adString;

	private int size;
	private boolean isInfiniteLoop;
	private OnItemClickListener mOnItemClickListener = null;

	public ImagePagerAdapter(Context context, List<Integer> imageIdList, List<String>list) {
		this.context = context;
		this.imageIdList = imageIdList;
		this.size = imageIdList.size();
		this.adString = list;
		isInfiniteLoop = true;
	}

	@Override public int getCount() {
		// Infinite loop
		return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
	}

	/**
	 * get really position
	 * 
	 * @param position
	 * @return
	 */
	private int getPosition(int position) {
		return isInfiniteLoop ? position % size : position;
	}

	@Override public View getView(final int position, View view,
			ViewGroup container) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.iv_ad_item, null);
			holder = new ViewHolder(view);
//			view = holder.imageView = new ImageView(context);
			view.setTag(holder);
			
			holder.framelayout.setOnClickListener(new OnClickListener() {

				@Override public void onClick(View v) {
					if (mOnItemClickListener != null) {
						mOnItemClickListener.onItemClick(position, v);
					}
				}
			});

		} else {
			holder = (ViewHolder) view.getTag();
		}
		/*holder.imageView.setImageResource(imageIdList
				.get(getPosition(position)));*/
		holder.framelayout.setBackgroundResource(imageIdList.get(getPosition(position)));
		holder.tv_ad_title.setText(adString.get(getPosition(position)));
		return view;
	}

	 static class ViewHolder {
		
//		@InjectView(R.id.ad_title_iv) ImageView imageView;
		@InjectView(R.id.framelayout) FrameLayout framelayout;
		@InjectView(R.id.tv_ad_title) TextView tv_ad_title;
		
		public ViewHolder(View v) {
			// TODO Auto-generated constructor stub
			ButterKnife.inject(this, v);
		}
	}

	/**
	 * @return the isInfiniteLoop
	 */
	public boolean isInfiniteLoop() {
		return isInfiniteLoop;
	}

	/**
	 * @param isInfiniteLoop
	 *            the isInfiniteLoop to set
	 */
	public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
		this.isInfiniteLoop = isInfiniteLoop;
		return this;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	public interface OnItemClickListener {
		/**
		 * 
		 * @param position
		 *            当前位置
		 * @param view
		 *            点击的那个view
		 */
		public void onItemClick(int position, View view);
	}

}
