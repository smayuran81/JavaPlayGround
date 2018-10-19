package com.test.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer2 {

	public static void main(String[] args) {

	}

	static class Buffer {

		private Queue<Integer> list;
		private int size;

		public Buffer(int size) {
			this.list = new LinkedList<>();
			this.size = size;
		}
		
		
		public void add(int value) throws InterruptedException {
			synchronized (this) {
				
				while(list.size()>=this.size) {
					wait();
				}
				
				list.add(value);
				notify();
				
			}
		}
		
		
		public int poll() throws InterruptedException {
			
			synchronized (this) {
				
				while(list.size()==0) {
					
					wait();
					
				}
				
				int value=list.poll();
				notify();
				
				return value;
				
			}
			
		}

	}

}
