package com.example.judgeparameter.aop;


import com.example.judgeparameter.result.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * SetPropertyName:非空参数切面类
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/20
 */
@Component
@Aspect
public class AopParameter {
    private static final Logger logger = LoggerFactory.getLogger(AopParameter.class);
    static final String split = ",";

    @Pointcut("@annotation(com.example.judgeparameter.aop.RequestRequire)")
    public void controllerInteceptor() {
    }

    /**
     * controller层增强类，用于检测参数为空的情况,低侵入式,不影响已经写好的代码
     *
     * @return java.lang.Object
     * @throws
     * @params pjp
     **/
    @Around("controllerInteceptor()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //参数非空返回给前端的
        ResponseData<Object> responseData = new ResponseData<>();
        Map map = new HashMap();

        // 获取注解的 方法参数列表
        Object[] args = pjp.getArgs();

        // 获取被注解的 方法
        MethodInvocationProceedingJoinPoint mjp = (MethodInvocationProceedingJoinPoint) pjp;
        MethodSignature signature = (MethodSignature) mjp.getSignature();
        Method method = signature.getMethod();

        // 获取方法上的 注解
        RequestRequire require = method.getAnnotation(RequestRequire.class);


        // 以防万一，将中文的逗号替换成英文的逗号,并且把空格去掉了
        String fieldNames = require.require().replace("，", ",").replace(" ", "");
        //logger.info("fieldNames==>" + fieldNames);

        // 从参数列表中获取参数对象 就是注解如parameter = LoginUser.class的类
        Object parameter = null;
        for (Object pa : args) {
            //class相等表示是同一个对象
            if (pa.getClass() == require.parameter()) {
                parameter = pa;
                // logger.info("从参数列表中获取参数对象:" + parameter.toString());
            }
        }

        // 通过反射去和指定的属性值判断是否非空
        // 获得参数的class 就是待判断参数的类
        Class aClass = parameter.getClass();


        // 遍历参数，找到是否为空
        for (String name : fieldNames.split(split)) {
            //需要判断接口参数是否和请求的对象属性一致,所以异常包裹一下
            Field declaredField;
            try {
                //反射获取属性
                declaredField = aClass.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                responseData.setCode(500);
                responseData.setMsg("后台接口非空参数错误:" + name);
                e.printStackTrace();
                return responseData;
            } catch (SecurityException e) {
                responseData.setCode(500);
                responseData.setMsg("SecurityException:" + name);
                e.printStackTrace();
                return responseData;
            }
            String fieldName = declaredField.getName();
            //反射的常规操作
            declaredField.setAccessible(true);
            //获取属性
            Object fieldObject = declaredField.get(parameter);
            // 获取属性的中文名称
            SetPropertyName spv = declaredField.getAnnotation(SetPropertyName.class);
            //就是判断RequestRequire注解里面的参数,如果不为空就赋值
            if (spv != null && StringUtils.isNotBlank(spv.value())) {
                fieldName = spv.value();
            }
            //如果属性为空就放入map,跳出循环后并给返回对象ResultVO<Object>
            if (fieldObject == null) {
                map.put(fieldName, "该参数不能为空!");
                responseData.setData(map);
                continue;

            }

            // 如果type是类类型，则前面包含"class "，后面跟类名
            if ("class java.lang.String".equals(declaredField.getGenericType().toString())) {
                if (StringUtils.isBlank((String) fieldObject)) {
                    map.put(fieldName, "该参数不能为空!");
                    responseData.setData(map);
                    continue;
                }
            }
            // 如果是数字类型的---留着拓展的(时间类型,文件类型)
            if ("class java.lang.Integer".equals(declaredField.getGenericType().toString())
                    || "class java.lang.Long".equals(declaredField.getGenericType().toString())
                    || "class java.lang.Double".equals(declaredField.getGenericType().toString())
                    /*这里是出现的实体类*/
                    || "com.example.judgeparameter.entity.Car".equals(declaredField.getGenericType().toString())
                    || "class java.lang.Float".equals(declaredField.getGenericType().toString())) {
                if (fieldObject == null) {
                    map.put(fieldName, "该参数不能为空!");
                    responseData.setData(map);
                }
                System.out.println("进入了判断类型==========================");

            }

        }


        if (responseData != null && responseData.getData() != null) {
            responseData.setCode(500);
            responseData.setMsg("请求的参数为空或者拼写错误,导致后台无法正常接收(也可能后台没有采用json格式接收),返回详情见data!");
            //data在循环的时候已经放进了map了,所以这里其实data有数据的
            return responseData;
        }
        // 如果没有报错，放行
        return pjp.proceed();
    }

}
