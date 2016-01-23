package com.herotculb.qunhaichat.crm.notes;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.crm.querychance.GetLinkmanByIdHandler;
import com.herotculb.qunhaichat.crm.querychance.GetLinkmanByIdThread;
import com.herotculb.qunhaichat.dto.NotesDto;
import com.herotculb.qunhaichat.dto.NotesInfoDto;
import com.herotculb.qunhaichat.util.DateUtil;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class QueryNotesItemAdapter extends BaseAdapter {
	private Activity  context;
	private List<NotesInfoDto> list;
	private String marks;
	
	public QueryNotesItemAdapter(Activity context, List<NotesInfoDto> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final NotesInfoDto dto = (NotesInfoDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.crm_query_notes_item_list, null);
		TextView text  = (TextView) gridLayout.findViewById(R.id.crm_notes_item_title);
		TextView text1 = (TextView) gridLayout.findViewById(R.id.crm_notes_item_date);
		TextView text2 = (TextView) gridLayout.findViewById(R.id.crm_notes_item_username);
		BootstrapButton check =(BootstrapButton) gridLayout.findViewById(R.id.crm_notes_item_check);
		Date date = new Date();
		Long ndate = Long.parseLong(dto.getNotesDate().toString());
		date.setTime(ndate);
		String notesDate = DateUtil.formatDateYYYY_MM_DD(date);
		text.setText(dto.getNotesTitle());
		text1.setText(notesDate);
		text2.setText(dto.getNotesUserName());
		marks = dto.getNotesMark();
		check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 查看详细
				Intent i = new Intent(context,NotesLinkWindow.class);
				Bundle b = new Bundle();
				b.putString("marks", marks);
				i.putExtras(b);  
	            context.startActivityForResult(i, 0);
	            context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		return gridLayout;
	}

}
