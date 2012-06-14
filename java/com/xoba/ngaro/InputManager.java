package com.xoba.ngaro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.xoba.ngaro.inf.IOManager;

public class InputManager implements IOManager {

	private final java.util.Stack<InputStream> stack = new java.util.Stack<InputStream>();

	public InputManager() {
		stack.push(System.in);
	}

	@Override
	public void pushInputName(String name) {
		try {
			stack.push(new FileInputStream(new File(name)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int read() {
		try {
			while (stack.size() > 0) {
				InputStream in = stack.peek();
				int b = in.read();
				if (b >= 0) {
					return b;
				} else {
					in.close();
					stack.pop();
				}
			}
			return -1;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(int c) {
		System.out.write(c);
	}

}