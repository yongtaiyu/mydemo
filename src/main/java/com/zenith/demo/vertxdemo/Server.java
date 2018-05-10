package com.zenith.demo.vertxdemo;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class Server extends AbstractVerticle {

	public void start() { // 服务一发布就会执行start
		Router router = Router.router(vertx);
		System.out.println("enter");

		vertx.setPeriodic(1000, id -> {
			System.out.println("timer"+id); // 每1000毫秒，打印一次，也就是说1000毫秒的逻辑执行完成，后面的就会被触发
		});
		HttpServer server = vertx.createHttpServer();

		Handler handler;
		router.routeWithRegex("/sp1").handler( // 对url请求进行拦截并处理
				// ctx应用上下文
				ctx -> vertx.eventBus().<String> send( // vertx.eventBus()事件总线，可以与参数里面的地址进行通信，能够在所有vertx之间进行通信
														// <String>
														// 向服务发送的Message里面包含的类型。可以是对象等
						Service01.URL01, // 请求转发的地址，交由相应的服务对事件进行处理
						"Hello Vertx1", // Message中的内容。有上面的String决定<String>
						result -> { // 服务将事件处理完成，返回的结果
							if (result.succeeded()) // 处理成功后执行的逻辑
								System.out.println(result.result().body());
							// ctx.reroute("");可以进行二级路由
							System.out.println("testi1");
							ctx.response() // 响应，和面可以跟响应内容，如setstatus()等
									.end();
						}));
		System.out.println("test1");

		router.routeWithRegex("/sp2")
				.handler(ctx -> vertx.eventBus().<String> send(Service01.URL02, "Hello Vertx2", result -> {
					if (result.succeeded()) // 如果成功，后台将会通知来执行这个。
						System.out.println(result.result().body());
					System.out.println("testi2");
					ctx.response().end();
				}));
		System.out.println("test2");

		router.routeWithRegex("/sp3")
				.handler(ctx -> vertx.eventBus().<String> send(Service03.URL03, "Hello Vertx3", result -> {
					if (result.succeeded()) // 如果成功，后台将会通知来执行这个。
						System.out.println(result.result().body());
					System.out.println("testi3");
					ctx.response().end();
				}));
		System.out.println("test3");

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}
}