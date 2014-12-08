package org.meizhuo.app.acty;

import java.util.ArrayList;
import java.util.HashMap;

import org.meizhuo.adapter.ChatAdapter;
import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import butterknife.InjectView;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class PublicConsultCultivate extends BaseActivity {

	ArrayList<HashMap<String, Object>> chatList = null;
	String[] from = { "image", "text" };
	int[] to = { R.id.chat_contact1, R.id.chat_text1, R.id.chat_contact2,
			R.id.chat_text2 };
	int[] layout = { R.layout.chat_contact1, R.layout.chat_contact2 };

	public final static int OTHER = 1;
	public final static int Me = 0;
	@InjectView(R.id.send_btn) ImageView chatSendbtn;
	@InjectView(R.id.send_et) EditText chatSendet;
	@InjectView(R.id.chat_list) ListView chatlv;
	ChatAdapter adapter;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.acty_publicconsultchat);
		setAppTitle("培训咨询");

		chatList = new ArrayList<HashMap<String, Object>>();
		addTextToList("有兴趣跟我们谈合作吗？", Me);
		addTextToList("可以！", OTHER);
		addTextToList("找个地方吃饭怎样，坐下来慢慢聊", Me);
		addTextToList("好的，时间地点", OTHER);
		addTextToList("晚上七点龙泉酒店", Me);
		addTextToList("不见不散", OTHER);

		adapter = new ChatAdapter(this, chatList, layout, from, to);
		chatlv.setAdapter(adapter);

	}

	@OnClick(R.id.send_btn) public void send() {
		String myWord = null;
		myWord = (chatSendet.getText() + "").toString();
		if (myWord.length() == 0)
			return;
		chatSendet.setText("");
		addTextToList(myWord, Me);
		/*
		 * 更新数据
		 */
		adapter.notifyDataSetChanged();
		chatlv.setSelection(chatList.size() - 1);
	}

	public void addTextToList(String text, int who) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("person", who);
		map.put("image", who == Me ? R.drawable.aa_headshot
				: R.drawable.aa_talker);
		map.put("text", text);
		chatList.add(map);
	}

}
