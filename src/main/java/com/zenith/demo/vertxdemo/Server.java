package com.zenith.demo.vertxdemo;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class Server extends AbstractVerticle {

	public void start() { // ����һ�����ͻ�ִ��start
		Router router = Router.router(vertx);
		System.out.println("enter");

		vertx.setPeriodic(1000, id -> {
			System.out.println("timer"+id); // ÿ1000���룬��ӡһ�Σ�Ҳ����˵1000������߼�ִ����ɣ�����ľͻᱻ����
		});
		HttpServer server = vertx.createHttpServer();

		Handler handler;
		router.routeWithRegex("/sp1").handler( // ��url����������ز�����
				// ctxӦ��������
				ctx -> vertx.eventBus().<String> send( // vertx.eventBus()�¼����ߣ��������������ĵ�ַ����ͨ�ţ��ܹ�������vertx֮�����ͨ��
														// <String>
														// ������͵�Message������������͡������Ƕ����
						Service01.URL01, // ����ת���ĵ�ַ��������Ӧ�ķ�����¼����д���
						"Hello Vertx1", // Message�е����ݡ��������String����<String>
						result -> { // �����¼�������ɣ����صĽ��
							if (result.succeeded()) // ����ɹ���ִ�е��߼�
								System.out.println(result.result().body());
							// ctx.reroute("");���Խ��ж���·��
							System.out.println("testi1");
							ctx.response() // ��Ӧ��������Ը���Ӧ���ݣ���setstatus()��
									.end();
						}));
		System.out.println("test1");

		router.routeWithRegex("/sp2")
				.handler(ctx -> vertx.eventBus().<String> send(Service01.URL02, "Hello Vertx2", result -> {
					if (result.succeeded()) // ����ɹ�����̨����֪ͨ��ִ�������
						System.out.println(result.result().body());
					System.out.println("testi2");
					ctx.response().end();
				}));
		System.out.println("test2");

		router.routeWithRegex("/sp3")
				.handler(ctx -> vertx.eventBus().<String> send(Service03.URL03, "Hello Vertx3", result -> {
					if (result.succeeded()) // ����ɹ�����̨����֪ͨ��ִ�������
						System.out.println(result.result().body());
					System.out.println("testi3");
					ctx.response().end();
				}));
		System.out.println("test3");

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}
}