package com.bc.stdsys.util;

import java.util.ArrayList;

import com.bc.stdsys.entitys.Student;

/**
 * 一般工具类
 * 
 * @author 马伊宗
 *
 */
public class Localutil {

	public static int totalPage(int total, int pageSize) {
		int totalP = 0;
		if (total % pageSize == 0) {
			totalP = total / pageSize;
		} else {
			totalP = total / pageSize + 1;
		}
		return totalP;
	}
	/**
	 * 分页小工具 说明： 1.objects:所有对象的集合 2.pageSize:每页的条数3.pageNow:当前页
	 * 
	 */
	public static ArrayList<Student> findStudentByPage(ArrayList<Student> objects, int pageSize, int pageNow) {
		ArrayList<Student> arrayList = new ArrayList<Student>();// 每页的对象集合（返回值）
		int total = objects.size();// 总条数
		int pageTotal = totalPage(objects.size(), pageSize);// 总页数
		for (int i = 0; i < pageTotal; i++) {
			if (i == (pageNow - 1)) {
				if (total == pageSize) {// 总条数等于每页条数
					for (int j = i * pageSize; j < pageSize; j++) {
						arrayList.add(objects.get(j));
					}
					return arrayList;
				} else if (total < pageSize) {// 总条数小于每页条数
					for (int j = i * pageSize; j < i * pageSize + total; j++) {
						arrayList.add(objects.get(j));
					}
					return arrayList;
				} else {// 总条数大于每页条数
					if (total % pageSize == 0) {//总条数和页容量可以整除
						for (int j = i * pageSize; j < i * pageSize + pageSize; j++) {
							arrayList.add(objects.get(j));
						}
						return arrayList;
					} else {//总条数和页容量不可以整除
						if (pageNow == pageTotal) {// 最后一页
							for (int j = i * pageSize; j < i * pageSize + total % pageSize; j++) {
								arrayList.add(objects.get(j));
							}
							return arrayList;
						} else {// 中间页
							for (int j = i * pageSize; j < i * pageSize + pageSize; j++) {
								arrayList.add(objects.get(j));
							}
							return arrayList;
						}
					}

				}
			}
		}
		return arrayList;
	}


}
