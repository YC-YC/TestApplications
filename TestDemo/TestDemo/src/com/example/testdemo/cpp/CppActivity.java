package com.example.testdemo.cpp;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 *@Author Administrator
 *@Time 2016-2-23
 */
public class CppActivity extends Activity {

	private int[] array = new int[]{
//			3,9,7,6,5,1
			1,9,10,7,4,5,2
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cpp_main);
	}
	
	public void doClick(View view)
	{
		switch (view.getId()) {
		case R.id.button1:
			printArray();
			LOG("bullble------------");
			bullble();
			printArray();
//			bullble2();
			LOG("insertSort------------");
			insertSort();
			printArray();
			LOG("quickSort------------");
			quickSort(0, array.length-1);
			printArray();
			break;

		default:
			break;
		}
	}

	private void printArray()
	{
		LOG("start");
		for (int item: array)
		{
			LOG(item + ",");
		}
		LOG("end");
	}
	private void LOG(String string) {
		Log.i("TestCpp", string);
	}

	//冒泡排序,从大到小
	private void bullble() {
		int temp;
		for (int i = 0; i < array.length - 1; i++)//次数
		{
			for (int j = i +1; j < array.length; j++)//没排序个数
			{
				if (array[i] < array[j])
				{
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	//冒泡排序改进,有问题
		private void bullble2() {
			int temp;
			int flag = 1;
			for (int i = 0; (i < array.length - 1) && flag > 0; i++)//次数
			{
				flag = 0;
				for (int j = i +1; j < array.length; j++)//没排序个数
				{
					if (array[i] > array[j])
					{
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
						flag = 1;
					}
				}
			}
		}
		//插入排序，将没排序的元素放到已排序列表中合适位置，从小到大
		private void insertSort() {
			int temp;
			for (int i = 1; i < array.length; i++)//没排序个数
			{
					temp = array[i];
					for (int j = i - 1; j >= 0; j--)//已排序列表
					{
						if (array[j] < temp)
						{
							array[j+1] = array[j];
						}
						else
						{
							break;
						}
					}
			}
		}
		//快速排序，选择基准点，大的在其前面，小的在其后面，递归直到个数只有一个，从大到小
		private void quickSort(int left, int right) {
			if (left < right)
			{
				int k = parition(left, right);
				quickSort(left, k-1);
				quickSort(k+1, right);
			}
		}

		//获取基准点下标
		private int parition(int left, int right) {
			int key = array[right];	//最后一个为基准点
			int i = left-1;	//指向左边小于基准点的位置
			int temp;
			for (int j = left; j < right; j++)
			{
				if (array[j] < key)
				{
					i++;
					temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
			//将基准点放在合适的位置
			temp = array[i+1];
			array[i+1] = array[right];
			array[right] = temp;
			return i+1;
		}
		private int parition2(int low, int high) {
			int key = array[low];	//第一个为基准点

			while(low < high)
			{
				//从后面找到一个合适的值与前面的交换
				while (low < high && array[high] >= key)
					high--;
				swap(low,high);
				//从前面找到一个合适的值与后面的交换
				while (low < high && array[low] <= key)
					low++;
				swap(high,low);
			}
			return low;
		}

		private void swap(int low, int high) {
			int temp = array[low];
			array[low] = array[high];
			array[high] = temp;
		}
}
