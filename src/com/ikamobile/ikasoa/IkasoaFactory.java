package com.ikamobile.ikasoa;

import java.util.List;
import java.util.Map;

import org.sulei.core.thrift.Factory;
import org.sulei.core.thrift.service.Service;

/**
 * IKASOA工厂接口
 * 
 * @author <a href="mailto:larry7696@gmail.com">Larry</a>
 * @version 0.1
 */
public interface IkasoaFactory extends Factory {

	/**
	 * 获取IKASOA客户端
	 * 
	 * @param iClass
	 *            应用接口类
	 * @param serverHost
	 *            服务地址
	 * @param serverPort
	 *            服务端口
	 * @return T IKASOA客户端
	 */
	public <T> T getIkasoaClient(final Class<T> iClass, final String serverHost, final int serverPort);

	/**
	 * 获取IKASOA客户端
	 * 
	 * @param iClass
	 *            应用接口类
	 * @param serverHostList
	 *            服务地址列表(将会通过默认负载均衡策略进行调用)
	 * @param serverPort
	 *            服务端口
	 * @return T IKASOA客户端
	 */
	public <T> T getIkasoaClient(final Class<T> iClass, final List<String> serverHostList, final int serverPort);

	/**
	 * 获取IKASOA服务端
	 * 
	 * @param implClass
	 *            应用接口实现类
	 * @param serverPort
	 *            服务端口
	 * @return IkasoaServer IKASOA服务端
	 * @exception IkasoaException
	 */
	public IkasoaServer getIkasoaServer(Class<?> implClass, int serverPort) throws IkasoaException;

	/**
	 * 获取IKASOA服务端
	 * 
	 * @param implClsCon
	 *            应用接口实现集合对象
	 * @param serverPort
	 *            服务端口
	 * @return IkasoaServer IKASOA服务端
	 * @exception IkasoaException
	 */
	public IkasoaServer getIkasoaServer(ImplClsCon implClsCon, int serverPort) throws IkasoaException;

	/**
	 * 获取IKASOA服务端
	 * 
	 * @param ImplClsConList
	 *            应用接口实现集合列表
	 * @param serverPort
	 *            服务端口
	 * @return IkasoaServer IKASOA服务端
	 * @exception IkasoaException
	 */
	public IkasoaServer getIkasoaServer(List<ImplClsCon> ImplClsConList, int serverPort) throws IkasoaException;

	/**
	 * 获取IKASOA服务端
	 * 
	 * @param serverPort
	 *            服务端口
	 * @param serviceMap
	 *            服务Map
	 * @return IkasoaServer IKASOA服务端
	 * @exception IkasoaException
	 */
	public IkasoaServer getIkasoaServer(int serverPort, Map<String, Service> serviceMap) throws IkasoaException;

}
