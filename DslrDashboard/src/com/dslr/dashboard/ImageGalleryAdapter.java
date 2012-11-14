// Copyright 2012 by Zoltan Hubai <hubaiz@gmail.com>
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version as long the source code includes
// this copyright notice.
// 

package com.dslr.dashboard;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ImageGalleryAdapter extends BaseAdapter {

	private final static String TAG = "ImageGalleryAdapter";

	public interface SelectionChangedListener {
		public void onSelectionChanged(
				ArrayList<ImageObjectHelper> selectedItems);
	}

	public interface ImageItemClickedListener {
		public void onImageItemClicked(ImageObjectHelper obj);
	}

	private SelectionChangedListener _selectionChangedListener;

	public void setOnSelectionChanged(SelectionChangedListener listener) {
		_selectionChangedListener = listener;
	}

	private ImageItemClickedListener _imageItemClickedListener;

	public void setOnImageItemClicked(ImageItemClickedListener listener) {
		_imageItemClickedListener = listener;
	}

	private ArrayList<ImageObjectHelper> _items;

	public ArrayList<ImageObjectHelper> items() {
		return _items;
	}

	public Context context;
	public LayoutInflater inflater;

	public ImageGalleryAdapter(Context context,
			ArrayList<ImageObjectHelper> arrayList) {
		super();

		// Log.d(TAG, "Costructor");

		this.context = context;
		this._items = arrayList;

		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		BitmapManager.INSTANCE.setPlaceholder(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.ic_launcher));

	}

	public void changeItems(ArrayList<ImageObjectHelper> arrayList) {
		// Log.d(TAG, "changeItems");
		_items = arrayList;
		notifyDataSetChanged();
	}

	public void addImgItem(ImageObjectHelper item) {
		// Log.d(TAG, "addImgItem");
		_items.add(item);
		this.notifyDataSetChanged();
	}

	public void selectAll() {
		// Log.d(TAG, "selectAll");
		for (ImageObjectHelper item : _items) {
			item.isChecked = true;
		}
		notifyDataSetChanged();
	}

	public void invert() {
		Log.d(TAG, "invert");
		for (ImageObjectHelper item : _items) {
			item.isChecked = !item.isChecked;
		}
		notifyDataSetChanged();
	}

	public int getCount() {
		// Log.d(TAG, "getCount: " + _items.size());
		return _items.size();
	}

	public Object getItem(int position) {
		// Log.d(TAG, "getItem: " + position);
		return _items.get(position);
	}

	public long getItemId(int position) {
		// Log.d(TAG, "getItemId: " + position);
		return position;
	}

	@Override
	public boolean hasStableIds() {
		// Log.d(TAG, "hasSTableIds");
		return true;
	}

	@Override
	public int getItemViewType(int position) {
		return IGNORE_ITEM_VIEW_TYPE;
	}

	@Override
	public int getViewTypeCount() {
		// Log.d(TAG, "getViewTypeCount");
		return 1;
	}

	public static class ViewHolder {
		CheckableLinearLayout itemLayout;
		ImageView thumbImage;
		TextView imgName;
		//CheckBox checkBox;

	}

	public View getView(int position, View convertView, ViewGroup parent) {

		// Log.d(TAG, "getView");
		final ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.img_preview_item, null);

			holder.itemLayout = (CheckableLinearLayout) convertView.findViewById(R.id.img_item_layout);
			holder.imgName = (TextView) convertView.findViewById(R.id.imgName);
			//holder.checkBox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);
			holder.thumbImage = (ImageView) convertView.findViewById(R.id.thumbImage);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		ImageObjectHelper helper = _items.get(position);

		
		holder.thumbImage.setId(position);
		
		//convertView.setSelected(helper.isChecked);
		holder.itemLayout.setChecked(helper.isChecked);
		//holder.checkBox.setId(position);
		switch (helper.galleryItemType) {
		case ImageObjectHelper.DSLR_PICTURE:
			holder.imgName.setText(helper.objectInfo.filename);
//			holder.thumbImage.setOnClickListener(new View.OnClickListener() {
//
//				public void onClick(View v) {
//					int id = v.getId();
//					ImageObjectHelper imgObj = _items.get(id);
//					if (_imageItemClickedListener != null)
//						_imageItemClickedListener.onImageItemClicked(imgObj);
//
//				}
//			});
//			holder.checkBox.setChecked(helper.isChecked);
//			holder.checkBox.setVisibility(View.VISIBLE);
//			holder.checkBox.setOnClickListener(new OnClickListener() {
//
//				public void onClick(View v) {
//					CheckBox cb = (CheckBox) v;
//					int id = cb.getId();
//					ImageObjectHelper imgObj = _items.get(id);
//					if (imgObj.isChecked) {
//						cb.setChecked(false);
//						imgObj.isChecked = false;
//					} else {
//						cb.setChecked(true);
//						imgObj.isChecked = true;
//					}
//					if (_selectionChangedListener != null)
//						_selectionChangedListener.onSelectionChanged(_items);
//				}
//			});

			break;

		case ImageObjectHelper.PHONE_PICTURE:
			holder.imgName.setText(helper.file.getName());
//			holder.thumbImage.setOnClickListener(new OnClickListener() {
//
//				public void onClick(View v) {
//					int id = v.getId();
//					ImageObjectHelper imgObj = _items.get(id);
//
//					if (_imageItemClickedListener != null)
//						_imageItemClickedListener.onImageItemClicked(imgObj);
//
//				}
//			});
//			holder.checkBox.setChecked(helper.isChecked);
//			holder.checkBox.setVisibility(View.VISIBLE);
//			holder.checkBox.setOnClickListener(new OnClickListener() {
//
//				public void onClick(View v) {
//					CheckBox cb = (CheckBox) v;
//					int id = cb.getId();
//					ImageObjectHelper imgObj = _items.get(id);
//					if (imgObj.isChecked) {
//						cb.setChecked(false);
//						imgObj.isChecked = false;
//					} else {
//						cb.setChecked(true);
//						imgObj.isChecked = true;
//					}
//					if (_selectionChangedListener != null)
//						_selectionChangedListener.onSelectionChanged(_items);
//				}
//			});

			break;
		}
		BitmapManager.INSTANCE.loadBitmap(helper.file.getAbsolutePath(),
				holder.thumbImage);

		return convertView;
	}

}