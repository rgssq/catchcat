package com.android;



import android.app.Activity;

import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.GridView;

import android.widget.Toast;

public class Catchcat extends Activity {
    private GridView gridview;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gridview = (GridView)findViewById(R.id.gridview);
        //gridview.setStretchMode(0);
        setAdapter(savedInstanceState);
    }
    protected void setAdapter(Bundle savedInstanceState)
    {
    	Integer[] thumIds=(savedInstanceState == null)?null:
    		(Integer[])savedInstanceState.getSerializable(ImageAdapter.THUMBIDS);
    	
    	ImageAdapter imageAdapter = new ImageAdapter(this);
    	if(thumIds != null)
    		imageAdapter.setThumbIds(thumIds);
    	gridview.setAdapter(imageAdapter);
        Toast.makeText(Catchcat.this, "�׸�����--��ţ", Toast.LENGTH_SHORT).show();
        imageAdapter.notifyDataSetChanged();
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(Catchcat.this, "" + position, Toast.LENGTH_SHORT).show();
            	
            	 
            	if((!((ImageAdapter)parent.getAdapter()).isTraped())&&(!((ImageAdapter)parent.getAdapter()).isOver()))
            	{
            		Integer vid=(Integer)parent.getAdapter().getItem(position);
                    //�������block�����ó�block������è����һ�������ж�
                	if(!vid.equals(R.drawable.block))
                    {
                		ImageView imageView =(ImageView)v;
                        imageView.setImageResource(R.drawable.block);
                        ((ImageAdapter)parent.getAdapter()).setImageResourceID(position,R.drawable.block);
                		int next=((ImageAdapter)parent.getAdapter()).WFS(position);
                		
                		int curCatPos=((ImageAdapter)parent.getAdapter()).getCurCatPos();
                		//�����·��
                		if(next>=0)
                    	{
                    		//���Ƚ���ǰè��λ����Ϊ��ɫ
                			((ImageView)parent.getChildAt(curCatPos)).setImageResource(R.drawable.blank);
                			//����ǰè��λ�øı�Ϊnext
                			((ImageAdapter)parent.getAdapter()).setCurCatPos(next);
                    		if(((ImageAdapter)parent.getAdapter()).isOver())
                    		{
                    			((ImageView)parent.getChildAt(next)).setImageResource(R.drawable.cat_sleepy);
                        		((ImageAdapter)parent.getAdapter()).setImageResourceID(next,R.drawable.cat_sleepy);
                    			Toast.makeText(Catchcat.this, "�����˹���", Toast.LENGTH_SHORT).show();
                    		}
                    		else
                    		{
                    			
                    			
                    			((ImageView)parent.getChildAt(next)).setImageResource(R.drawable.cat_still);
                        		((ImageAdapter)parent.getAdapter()).setImageResourceID(next,R.drawable.cat_still);
                    		}
                    	}
                    	else
                    	{
                    		
                    		((ImageView)parent.getChildAt(curCatPos)).setImageResource(R.drawable.cat_caught);
                    		((ImageAdapter)parent.getAdapter()).setImageResourceID(curCatPos,R.drawable.cat_caught);
                    		Toast.makeText(Catchcat.this, "��ס��", Toast.LENGTH_SHORT).show();
                    	}
                    	
                    }
               }
                
            }
        });
    }
    @Override 
    protected void onSaveInstanceState(Bundle outState) { 
        super.onSaveInstanceState(outState); 
        saveState(outState);
    }
    @Override 
    protected void onPause() { 
        super.onPause();  
    }
    protected void saveState(Bundle outState)
    {
    	outState.putSerializable(ImageAdapter.THUMBIDS, 
    			((ImageAdapter)gridview.getAdapter()).getThumbIds());
    }
    protected void onResume()
    {
    	super.onResume(); 
    }
    
}

