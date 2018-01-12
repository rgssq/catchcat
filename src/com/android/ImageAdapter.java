package com.android;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    //reference to resource
    private Integer[] mThumbIds;
    private int col=10;
    private int row=13;
    private int curCatPos;
   
    private int[] disToCat;
    private int[] prePosition;
    private Boolean found;
    private int exitPos;
    public static final String THUMBIDS="mThumbIds";
    public ImageAdapter(Context c) {
        mContext = c;
        int sum=col*row;
        mThumbIds = new Integer[sum];
        disToCat = new int[sum];
        prePosition = new int[sum];
        for(int tem =0;tem < sum;tem++)
        {
        	mThumbIds[tem]=R.drawable.blank;
        	disToCat[tem]=-1;
        	prePosition[tem]=-1;
        }
        Random random=new Random(System.currentTimeMillis());
        int catRow=random.nextInt(row-6);
        int catCol=random.nextInt(col-6);
        curCatPos=(catRow+3)*col+(catCol+3);
        //curCatPos=43;
        mThumbIds[curCatPos]=R.drawable.cat_still;
        this.found=true;
        this.exitPos=-1;
    }
    public Integer[] getThumbIds()
    {	
    	return mThumbIds;
    }
    public void setThumbIds(Integer[] iThumbIds)
    {
    	mThumbIds=iThumbIds;
    }

    protected void Initial()
    {
    	int sum =col*row;
    	for(int tem=0;tem<sum;tem++)
    	{
    		disToCat[tem]=-1;
        	prePosition[tem]=-1;
    	}
    	this.found=false;
    	this.exitPos=-1;
    }
    protected int blockDirection(int position)
    {
    	int curRow=this.curCatPos/col;
    	int curCol=this.curCatPos%col;
    	int blkRow=position/col;
    	int blkCol=position%col;
    	int result=0;
    	//上下左右
    	//正上方1000;左上方1010;右上方1001
    	//正下方0100;左下方0110;右下方0101
    	//正左方0010;正右方0001;
    	if(blkRow<curRow)
    	{
    		result+=8;
    	}
    	else if(blkRow>curRow)
    	{
    		result+=4;
    	}
    	if(blkCol<curCol)
			result+=2;
		else if(blkCol>curCol)
			result+=1;
    	return result;
    	
    }

    public int WFS(int position)
    {
    	Initial();
    	Queue<Integer> curPos=new LinkedList<Integer>();
    	this.prePosition[this.curCatPos]=-1;
    	this.disToCat[this.curCatPos]=0;
    	int first=0;
    	int second=0;
    	int third=0;
    	int forth=0;
    	int direction=this.blockDirection(position);
    	//正上方1000;左上方1010;右上方1001
    	//正下方0100;左下方0110;右下方0101
    	//正左方0010;正右方0001;
    	if(direction==8)
    	{
    		first=col;
    		second=-1;
    		third=1;
    		forth=-col;
    	}
    	else if(direction==10)
    	{
    		first=col;
    		second=1;
    		third=-1;
    		forth=-col;
    	}
    	else if(direction==9)
    	{
    		first=-1;
    		second=col;
    		third=+1;
    		forth=-col;
    	}
    	else if(direction==4)
    	{
    		first=-col;
    		second=-1;
    		third=1;
    		forth=col;
    	}
    	else if(direction==6)
    	{
    		first=-col;
    		second=1;
    		third=-1;
    		forth=col;
    	}
    	else if(direction==5)
    	{
    		first=-col;
    		second=-1;
    		third=1;
    		forth=col;
    	}
    	else if(direction==2)
    	{
    		first=+1;
    		second=-col;
    		third=col;
    		forth=-1;
    	}
    	else if(direction==1)
    	{
    		first=-1;
    		second=-col;
    		third=col;
    		forth=1;
    	}
    	else
    	;
    	Log.i("31:",String.valueOf(this.mThumbIds[31]));
    	//不需要判断猫的位置是否在边界，假设不在。移动之后需要判断
    	
    	if(this.mThumbIds[this.curCatPos+first]==R.drawable.block)
    		;
    	else
    		{
    		if(!this.isOnBorder(this.curCatPos+first)&&
    		   ((this.curCatPos+first-1>0 && 
    				   this.curCatPos+first+1>0 
    				   && mThumbIds[curCatPos+first-1]==R.drawable.block 
    				   && mThumbIds[curCatPos+first+1]==R.drawable.block)
    		 ||(this.curCatPos+first-col>0 && 
  				   this.curCatPos+first+col>0 
				   && mThumbIds[curCatPos+first-col]==R.drawable.block 
				   && mThumbIds[curCatPos+first+col]==R.drawable.block)))
    			;
    		else
    		{
    			Log.i("location ",String.valueOf(this.curCatPos+first));
        		curPos.offer(this.curCatPos+first);
        		this.disToCat[this.curCatPos+first]=1;
        		this.prePosition[this.curCatPos+first]=this.curCatPos;
    		}
    		
    		}
    	if(this.mThumbIds[this.curCatPos+second]==R.drawable.block)
    		;
    	else
    		{
    		if(!this.isOnBorder(this.curCatPos+second)&&
    	    		   ((this.curCatPos+second-1>0 && 
    	    				   this.curCatPos+second+1>0 
    	    				   && mThumbIds[curCatPos+second-1]==R.drawable.block 
    	    				   && mThumbIds[curCatPos+second+1]==R.drawable.block)
    	    		 ||(this.curCatPos+second-col>0 && 
    	  				   this.curCatPos+second+col>0 
    					   && mThumbIds[curCatPos+second-col]==R.drawable.block 
    					   && mThumbIds[curCatPos+second+col]==R.drawable.block)))
    	    			;
    	    		else
    	    		{
    	    			Log.i("location ",String.valueOf(this.curCatPos+second));
    	        		curPos.offer(this.curCatPos+second);
    	        		this.disToCat[this.curCatPos+second]=1;
    	        		this.prePosition[this.curCatPos+second]=this.curCatPos;
    	    		}
    		}
    	if(this.mThumbIds[this.curCatPos+third]==R.drawable.block)
    		;
    	else
    		{
    		if(!this.isOnBorder(this.curCatPos+third)&&
 	    		   ((this.curCatPos+third-1>0 && 
 	    				   this.curCatPos+third+1>0 
 	    				   && mThumbIds[curCatPos+third-1]==R.drawable.block 
 	    				   && mThumbIds[curCatPos+third+1]==R.drawable.block)
 	    		 ||(this.curCatPos+third-col>0 && 
 	  				   this.curCatPos+third+col>0 
 					   && mThumbIds[curCatPos+third-col]==R.drawable.block 
 					   && mThumbIds[curCatPos+third+col]==R.drawable.block)))
 	    			;
 	    		else
 	    		{
 	    			Log.i("location ",String.valueOf(this.curCatPos+third));
 	        		curPos.offer(this.curCatPos+third);
 	        		this.disToCat[this.curCatPos+third]=1;
 	        		this.prePosition[this.curCatPos+third]=this.curCatPos;
 	    		}
    		}
    	if(this.mThumbIds[this.curCatPos+forth]==R.drawable.block)
    		;
    	else
    		{
    		if(!this.isOnBorder(this.curCatPos+forth)&&
  	    		   ((this.curCatPos+forth-1>0 && 
  	    				   this.curCatPos+forth+1>0 
  	    				   && mThumbIds[curCatPos+forth-1]==R.drawable.block 
  	    				   && mThumbIds[curCatPos+forth+1]==R.drawable.block)
  	    		 ||(this.curCatPos+forth-col>0 && 
  	  				   this.curCatPos+forth+col>0 
  					   && mThumbIds[curCatPos+forth-col]==R.drawable.block 
  					   && mThumbIds[curCatPos+forth+col]==R.drawable.block)))
  	    			;
  	    		else
  	    		{
  	    			Log.i("location ",String.valueOf(this.curCatPos+forth));
  	        		curPos.offer(this.curCatPos+forth);
  	        		this.disToCat[this.curCatPos+forth]=1;
  	        		this.prePosition[this.curCatPos+forth]=this.curCatPos;
  	    		}
    		}
    	
    	while(curPos.size()!=0)
    	{
    		int pre=curPos.poll();
    		
    		if(!isOnBorder(pre))
    		//添加他的下一节点进队列，注意如果子孙已经访问过，因为这个是
    		//广度优先遍历，所以如果访问过，说明已经有更短的路径到这个位置
    		//不需要添加这个节点
    		{
    			Log.i("wfs.curPos.poll() row col",String.valueOf(pre/col)+" "+String.valueOf(pre%col));
    			Log.i("wfs curPos.poll() 1st,2nd,3rd,4th",String.valueOf(first)+" "+String.valueOf(second)
    					+" "+String.valueOf(third)+" "+String.valueOf(forth));
   			    if((this.mThumbIds[pre+first]!=R.drawable.block)
    					&&(this.disToCat[pre+first]<0))
    			{
   			    	if(!this.isOnBorder(pre+first)&&
   			    		   ((pre+first-1>0 && 
   			    				     pre+first+1>0 
   			    				   && mThumbIds[pre+first-1]==R.drawable.block 
   			    				   && mThumbIds[pre+first+1]==R.drawable.block)
   			    		 ||(pre+first-col>0 && 
   			  				   pre+first+col>0 
   							   && mThumbIds[pre+first-col]==R.drawable.block 
   							   && mThumbIds[pre+first+col]==R.drawable.block)))
   			    			;
   			    	else
   			    	{
   			    		curPos.offer(pre+first);
   	    				this.disToCat[pre+first]=this.disToCat[pre]+1;
   	    				this.prePosition[pre+first]=pre;
   			    	}
   			    	
    			}
    			if((this.mThumbIds[pre+second]!=R.drawable.block)
    					&&(this.disToCat[pre+second]<0))
    			{
    				if(!this.isOnBorder(pre+second)&&
    			    		   ((pre+second-1>0 && 
    			    				     pre+second+1>0 
    			    				   && mThumbIds[pre+second-1]==R.drawable.block 
    			    				   && mThumbIds[pre+second+1]==R.drawable.block)
    			    		 ||(pre+second-col>0 && 
    			  				   pre+second+col>0 
    							   && mThumbIds[pre+second-col]==R.drawable.block 
    							   && mThumbIds[pre+second+col]==R.drawable.block)))
    			    			;
    			    	else
    			    	{
    			    		curPos.offer(pre+second);
    	    				this.disToCat[pre+second]=this.disToCat[pre]+1;
    	    				this.prePosition[pre+second]=pre;
    			    	}
    			}
    			if((this.mThumbIds[pre+third]!=R.drawable.block)
    					&&(this.disToCat[pre+third]<0))
    			{
    				if(!this.isOnBorder(pre+third)&&
 			    		   ((pre+third-1>0 && 
 			    				     pre+third+1>0 
 			    				   && mThumbIds[pre+third-1]==R.drawable.block 
 			    				   && mThumbIds[pre+third+1]==R.drawable.block)
 			    		 ||(pre+third-col>0 && 
 			  				   pre+third+col>0 
 							   && mThumbIds[pre+third-col]==R.drawable.block 
 							   && mThumbIds[pre+third+col]==R.drawable.block)))
 			    			;
 			    	else
 			    	{
 			    		curPos.offer(pre+third);
 	    				this.disToCat[pre+third]=this.disToCat[pre]+1;
 	    				this.prePosition[pre+third]=pre;
 			    	}
    			}
    			if((this.mThumbIds[pre+forth]!=R.drawable.block)
    					&&(this.disToCat[pre+forth]<0))
    			{
    				if(!this.isOnBorder(pre+forth)&&
  			    		   ((pre+forth-1>0 && 
  			    				     pre+forth+1>0 
  			    				   && mThumbIds[pre+forth-1]==R.drawable.block 
  			    				   && mThumbIds[pre+forth+1]==R.drawable.block)
  			    		 ||(pre+forth-col>0 && 
  			  				   pre+forth+col>0 
  							   && mThumbIds[pre+forth-col]==R.drawable.block 
  							   && mThumbIds[pre+forth+col]==R.drawable.block)))
  			    			;
  			    	else
  			    	{
  			    		curPos.offer(pre+forth);
  	    				this.disToCat[pre+forth]=this.disToCat[pre]+1;
  	    				this.prePosition[pre+forth]=pre;
  			    	}
    			}
    		}
    		else
    		{
    			this.found=true;
    			this.exitPos=pre;
    			curPos.clear();
    			break;
    		}
    	}
    	//如果有路到边界，则回溯到相应的方向
    	if(this.found)
    	{
    		int pre=this.prePosition[this.exitPos];
    		int cur=this.exitPos;
    		while(pre!=this.curCatPos)
    		{
    			cur=pre;
    			pre=this.prePosition[cur];
    		}
    		return cur;
    	}
    	else
    	{
    		return -1;
    	}
    	
    }

    public Boolean isOver()
    {
		//由界面调用WFS()之后调用，
    	//位置已经在border上
    	//true:逃脱 
    	if(this.isOnBorder(this.curCatPos))
    	{
    		return true;
    	}
    	return false;
    	
    }
    public Boolean isTraped()
    {
		if(!this.found)
			return true;
		return false;
    }
    public Boolean isOnBorder(int position)
    {
    	int posRow=position/col;
    	int posCol=position%col;

    	if((posRow == 0)||(posRow == (row-1))||(posCol == 0)||(posCol ==(col-1)))
    	{
    		return true;
    	}
    	return false;
    }
    public Boolean isEscaped()
    {
    	return isOnBorder(this.curCatPos);
    }
    
    
    public int getCurCatPos()
    {
    	return curCatPos;
    }
    public void setCurCatPos(int position)
    {
    	this.mThumbIds[curCatPos]=R.drawable.blank;
    	this.curCatPos=position;
    	
    }
    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }
    public void setImageResourceID(int position,int sourceID)
    {
    	mThumbIds[position]=sourceID;
    }
    
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(30, 30));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
            
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    
    
}