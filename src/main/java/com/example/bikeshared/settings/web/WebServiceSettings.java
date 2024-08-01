package com.example.bikeshared.settings.web;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs // Habilita o webservice
@Configuration // Indica que essa é uma classe de configuração
public class WebServiceSettings extends WsConfigurerAdapter {

    @Bean // Indica na framework que essa função precisa ser executada durante o processo de compilação 
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext contexto) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(contexto);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/baikeshared/*");
    }

    //-----  CONEXÃO DO USER SCHEMA XSD
    @Bean(name = "user")
    DefaultWsdl11Definition userWsdlDefinition(XsdSchema userSchema) {
        DefaultWsdl11Definition wsdl11def = new DefaultWsdl11Definition();
        wsdl11def.setPortTypeName("UserSoapHttpBikeShared");
        wsdl11def.setLocationUri("/baikeshared");
        wsdl11def.setTargetNamespace("http://user.soap.xml");
        wsdl11def.setSchema(userSchema);
        return wsdl11def;
    }

    @Bean // o schema do retorno é referência de src > main > resources > xsd > user.xsd
    XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/user.xsd"));
    }

    //-----  CONEXÃO DO STATION SCHEMA XSD
    @Bean(name = "station")
    DefaultWsdl11Definition stationWsdlDefinition(XsdSchema stationSchema) {
        DefaultWsdl11Definition wsdl11def = new DefaultWsdl11Definition();
        wsdl11def.setPortTypeName("StationSoapHttpBikeShared");
        wsdl11def.setLocationUri("/baikeshared");
        wsdl11def.setTargetNamespace("http://station.soap.xml");
        wsdl11def.setSchema(stationSchema);
        return wsdl11def;
    }

    @Bean // o schema do retorno é referência de src > main > resources > xsd > station.xsd
    XsdSchema stationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/station.xsd"));
    }
}
