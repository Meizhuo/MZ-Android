package org.meizhuo.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jakewharton.salvage.RecyclingPagerAdapter;
/**
 * 图片轮播(默认无限)
 * @author Trinea
 * @author Jayin
 *
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

	    private Context       context;
	    private List<Integer> imageIdList;

	    private int           size;
	    private boolean       isInfiniteLoop;
	    private OnItemClickListener mOnItemClickListener = null;

	    public ImagePagerAdapter(Context context, List<Integer> imageIdList) {
	        this.context = context;
	        this.imageIdList = imageIdList;
	        this.size = imageIdList.size();
	        isInfiniteLoop = true;
	    }

	    @Override
	    public int getCount() {
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

	    @Override
	    public View getView(final int position, View view, ViewGroup container) {
	        ViewHolder holder;
	        if (view == null) {
	            holder = new ViewHolder();
	            view = holder.imageView = new ImageView(context);
	            view.setTag(holder);
	            
	            holder.imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(mOnItemClickListener!=null){
				        	mOnItemClickListener.onItemClick(position, v);
				        }
					}
				});
	            
	        } else {
	            holder = (ViewHolder)view.getTag();
	        }
	        holder.imageView.setImageResource(imageIdList.get(getPosition(position)));
	       
	        return view;
	    }

	    private static class ViewHolder {

	        ImageView imageView;
	    }

	    /**
	     * @return the isInfiniteLoop
	     */
	    public boolean isInfiniteLoop() {
	        return isInfiniteLoop;
	    }

	    /**
	     * @param isInfiniteLoop the isInfiniteLoop to set
	     */
	    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
	        this.isInfiniteLoop = isInfiniteLoop;
	        return this;
	    }
	    
	   public void setOnItemClickListener(OnItemClickListener listener){
		   this.mOnItemClickListener = listener;
	   }  
	   
	   
	   public interface OnItemClickListener{
		   /**
		    * 
		    * @param position 当前位置
		    * @param view 点击的那个view
		    */
		   public void  onItemClick(int position,View view  );
	   }

}
