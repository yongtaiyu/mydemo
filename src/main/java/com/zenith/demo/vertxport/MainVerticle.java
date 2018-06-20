package com.zenith.demo.vertxport;

import java.io.IOException;
import java.util.function.Consumer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;

public class MainVerticle {
	// 程序运行主函数

	public static void main(String[] args) throws IOException {
		Consumer<Vertx> runner = vertx -> {
			Future<String> dbVerticleDeployment = Future.future(); // <1>
			vertx.deployVerticle(new TestVerticle1(), dbVerticleDeployment.completer()); // <2>

			dbVerticleDeployment.compose(id -> { // <3>
				Future<String> httpVerticleDeployment = Future.future();
				vertx.deployVerticle("com.zenith.demo.vertxport.TestVerticle2", // <4>
						new DeploymentOptions().setInstances(1), // <5>
						httpVerticleDeployment.completer());

				return httpVerticleDeployment; // <6>

			});

		};
		VertxOptions options = SetOptions(); // 设置启动参数 （通用配置）
		// 如果是单实例启动
		Vertx vertx = Vertx.vertx(options); // 绑定设置参数

		runner.accept(vertx);
	}

	// 设置服务启动参数配置
	public static VertxOptions SetOptions() {
		VertxOptions options = new VertxOptions();
		options.setMetricsOptions(
				new DropwizardMetricsOptions().setEnabled(true).setJmxEnabled(true).setJmxDomain("vertx-metrics"));
		/*
		 * 待扩展 ，根据参数配置文件设置的参数 ，配置服务启动参数
		 */
		return options;
	}

}
