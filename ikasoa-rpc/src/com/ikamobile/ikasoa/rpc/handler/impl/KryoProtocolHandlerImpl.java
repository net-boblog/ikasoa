package com.ikamobile.ikasoa.rpc.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.ikamobile.ikasoa.rpc.handler.ProtocolHandler;
import com.ikamobile.ikasoa.rpc.handler.ReturnData;
import com.ikamobile.ikasoa.rpc.utils.Base64Util;

/**
 * 转换协议处理器Kryo实现
 * 
 * @author <a href="mailto:larry7696@gmail.com">Larry</a>
 * @version 0.1
 */
public class KryoProtocolHandlerImpl<T1, T2> implements ProtocolHandler<T1, T2> {

	private ReturnData resultData;

	private Kryo kryo = new Kryo();

	public KryoProtocolHandlerImpl(ReturnData resultData) {
		this.resultData = resultData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T1 strToArg(String str) {
		return (T1) kryo.readObject(new Input(Base64Util.decode(str)), kryo.register(Object[].class).getType());
	}

	@SuppressWarnings("unchecked")
	@Override
	public String argToStr(T1 arg) {
		if (arg == null) {
			arg = (T1) new Object[0];
		}
		Output output = new Output(1, 4096);
		kryo.writeObject(output, arg);
		byte[] bb = output.toBytes();
		output.flush();
		return Base64Util.encode(bb);
	}

	@Override
	public String resultToStr(T2 result) {
		Output output = new Output(1, 4096);
		kryo.writeObject(output, result);
		byte[] bb = output.toBytes();
		output.flush();
		return Base64Util.encode(bb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T2 strToResult(String str) {
		if (resultData.isArray()) {
			return (T2) kryo.readObject(new Input(Base64Util.decode(str)),
					kryo.register((new ArrayList<>()).getClass()).getType());
		} else if (resultData.isMap()) {
			return (T2) kryo.readObject(new Input(Base64Util.decode(str)),
					kryo.register((new HashMap<>()).getClass()).getType());
		} else {
			return (T2) kryo.readObject(new Input(Base64Util.decode(str)),
					kryo.register(resultData.getClassType()).getType());
		}
	}

	@Override
	public Throwable strToThrowable(String str) {
		// TODO:该实现暂不对异常返回做处理
		return null;
	}

	@Override
	public ReturnData getReturnData() {
		return resultData;
	}

}
