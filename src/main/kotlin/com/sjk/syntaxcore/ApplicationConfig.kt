package com.sjk.syntaxcore

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ApplicationConfig : WebMvcConfigurer {

    @Bean
    fun appExecutor(): ThreadPoolTaskExecutor {
        val t = ThreadPoolTaskExecutor()

        t.corePoolSize = 350
        t.maxPoolSize = 350
        t.queueCapacity = 1000
        t.setAllowCoreThreadTimeOut(true)
        t.keepAliveSeconds = 120
        t.initialize()

        return t
    }
}
