package com.hf.happyfamily.config;

import com.hf.happyfamily.domain.Member;
import com.hf.happyfamily.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数
 */
@Configuration
public class RouterFunctionConfigration {

    /**
     * Servlet
     * 请求接口：ServletRequest或者 HttpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring 5.0 重新定义了服务请求和响应请求
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 既可以支持Servlet规范，也可以支持自定义，如Netty （Web Server）
     *
     * 本例：
     * 定义 GET请求，并且返回所有的家庭成员对象 URI： /member/find/all
     *
     * Flux 是0 - N个对象集合
     * Mono 是 0-1 个对象集合
     * Reactive中的Flux或者Mono是异步处理 （非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux，Mono 都是Publisher
     */

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> memberFindAll(MemberRepository memberRepository){
        return RouterFunctions.route(RequestPredicates.GET("/member/find/all"),
                request -> {
                    Collection<Member> members = memberRepository.findAll();
                    Mono<ServerResponse> responseMono =  null;
                    Flux<Member> memberFlux = Flux.fromIterable(members);
                    responseMono = ServerResponse.ok().body(memberFlux, Member.class);
                    return responseMono;
                });
    }
}
