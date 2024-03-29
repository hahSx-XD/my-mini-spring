my-mini-spring
===========================
# 介绍
造轮子项目01--自建的 Spring 框架。该项目在保证 Spring 框架的核心功能和思想不缺少的前提下进行了较多简化，只保留了核心功能，作为学习 Spring 框架使用。

# 编写目的
主要为了自己能更好的理解和掌握 Spring 框架的底层原理以及设计思想。
---

# IOC

---

## [最简单的 bean 容器](https://gitee.com/hahSx_XD/my-mini-spring/tree/simple-bean-container/)

> 代码分支：simple-bean-container

BeanFactory 类，`private Map<String, Object> beanMap = new HashMap<>();`存储 Bean。
**put、get 方法分别为 Bean 注册、Bean 获取。**

---

## [BeanDefinition 和 BeanDefinitionRegistry](https://gitee.com/hahSx_XD/my-mini-spring/tree/bean-definition-and-bean-definition-registry/)

> 代码分支：bean-definition-and-bean-definition-registry

主要增加

- BeanDefinition，顾名思义，用于定义 bean 信息的类，包含 bean 的 class 类型、构造参数、属性值等信息，每个 bean 对应一个 BeanDefinition 的实例。简化 BeanDefinition 仅包含 bean 的 class 类型。
- BeanDefinitionRegistry，BeanDefinition 注册表接口，定义注册 BeanDefinition 的方法。
- SingletonBeanRegistry 及其实现类 DefaultSingletonBeanRegistry，定义添加和获取单例 bean 的方法。

bean 容器作为 BeanDefinitionRegistry 和 SingletonBeanRegistry 的实现类，具备两者的能力。向 bean 容器中注册 BeanDefinition 后，使用 bean 时才会实例化。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FsvbBD_0C7h44C1d8rT-BDxjpLnu.png)

### bean 单例注入过程

#### 注册

调用 DefaultListableBeanFactory 类的 `registryBeanDefinition()` 方法，在 DefaultListableBeanFactory 类的 beanDefinitionMap 字段中 `put(beanName, BeanDefinition)；`完成 bean 注入。

#### 实例化

1. 调用 DefaultListableBeanFactory 类的祖父类 AbstractBeanFactory 类的 `getBean()` 方法；
2. 调用 AbstractBeanFactory 类父类 DefaultSingletonBeanRegistry 类的 `getSingleton()` 方法，分为两种情况

---

3.  1.  字段 `Map<String, Object> singletonObjects` 存在 Key 为该名字的值 Object（即当前名字的 bean 已经实例化）
    2.  直接获得该实例返回

---

1.  `Map<String, Object> singletonObjects` 不存在 Key 为该名字的值 Object（即当前名字的 bean 未实例化）
2.  调用本类上的 abstract 方法 `getBeanDefinition()`，其在孙子类 DefaultListableBeanFactory 类上实现，去查找当前名字的 bean 是否已注册
3.  如果未注册，直接返回异常；如果已注册，返回对应的 BeanDefinition，去调用本类上的 abstract 方法 `createBean()`，其在子类 AbstractAutowireCapableBeanFactory 类上实现
4.  该类的 `createBean()` 调用类内方法 `doCreateBean()` ，先通过 BeanDefinition 拿到注册的 bean 的属性等各类信息，使用 `newInstance()` 方法得到 bean 实例，在通过其祖父类 DefaultSingletonBeanRegistry 的`addSingleton()` 方法将该 bean 实例存入 `Map<String, Object> singletonObjects` ，下次无需再次实例化，**单例模式**
5.  返回该实例

---

## [Bean 实例化策略 InstantiationStrategy](https://gitee.com/hahSx_XD/my-mini-spring/tree/instantiation-strategy/)

> 代码分支：instantiation-strategy

上节中 bean 是在 `AbstractAutowireCapableBeanFactory#doCreateBean()` 方法中用 `beanClass.newInstance ()` 来实例化，仅适用于 bean 有无参构造函数的情况。
针对 bean 的实例化，抽象出一个实例化策略的接口 InstantiationStrategy，有两个实现类：

- SimpleInstantiationStrategy，使用 bean 的构造函数来实例化
- CglibSubclassingInstantiationStrategy，使用 CGLIB 动态生成子类

![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FlK23JGcqIxc90Uh7fxvlSalwe4x.png)
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FuRQjss1s4sg41qj7tNJGymuR2XS.png)
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FiwLHqf6rmSeENZm5kAD40R5pzUp.png)

---

## [为 bean 填充属性信息](https://gitee.com/hahSx_XD/my-mini-spring/tree/populate-bean-with-property-values/)

> 代码分支：populate-bean-with-property-values

在 BeanDefinition 中增加和 bean 属性对应的 PropertyValues；
实例化 bean 之后，通过 `AbstractAutowireCapableBeanFactory#applyPropertyValues()` 为 bean 填充属性。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FiXP5aQ6aIH0FppY84Quq6QuNemq.png)
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FnpkLC1Tl15Oat62ZkJ4vr_ARtUd.png)")

#### 为什么 org.springframework.beans.PropertyValues 中的 public PropertyValue[] getPropertyValues() 不返回 List，而是 **return this**.propertyValueList.toArray(**new **PropertyValue[0]); ？

�

---

## [为 bean 填充 bean](https://gitee.com/hahSx_XD/my-mini-spring/tree/populate-bean-with-bean/)

> 代码分支：populate-bean-with-bean

增加 BeanReference 类，包装一个 bean 对另一个 bean 的引用。
实例化 beanA 后填充属性时，若 PropertyValue#value 为 BeanReference，引用 beanB，则先去实例化 beanB。
由于当前对整体代码理解有限，暂时未解决循环依赖，后续更新解决。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FospMWPsd5sTbxXuHgk1hohIbUSp.png)")

---

## [资源和资源加载器](https://gitee.com/hahSx_XD/my-mini-spring/tree/resource-and-resource-loader/)

> 代码分支：resource-and-resource-loader

![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FuDZ6rs_9SOpiN4nHHwlOjwfAOA_.png)

- 资源：
  - FileSystemResource，文件系统资源的实现类
  - ClassPathResource，classpath 下资源的实现类
  - UrlResource，对 java.net.URL 进行资源定位的实现类
- 资源加载器
  - ResourceLoader 接口则是资源查找定位策略的抽象
  - DefaultResourceLoader 是其默认实现类

---

## [在 xml 文件中定义 bean](https://gitee.com/hahSx_XD/my-mini-spring/tree/xml-file-define-bean/)

> 代码分支：xml-file-define-bean

上节实现的资源加载器可以读取文件内容，因此可以在 xml 配置文件中声明式地定义 bean 的信息，资源加载器读取 xml 文件，解析出 bean 的信息，然后往容器中注册 BeanDefinition。
BeanDefinitionReader 是读取 bean 定义信息的抽象接口，XmlBeanDefinitionReader 是从 xml 文件中读取的实现类。BeanDefinitionReader 需要有获取资源的能力，且读取 bean 定义信息后需要往容器中注册 BeanDefinition，因此 BeanDefinitionReader 的抽象实现类 AbstractBeanDefinitionReader 拥有 ResourceLoader 和 BeanDefinitionRegistry 两个属性。
由于从 xml 文件中读取的内容是 String 类型，所以属性仅支持 String 类型和引用其他 Bean。后续会使用属性编辑器 PropertyEditor，实现类型转换。
PS：为了方便理解和功能实现，并尽量保持和 spring 中 BeanFactory 的继承层次一致，对 BeanFactory 的继承层次稍微做了调整。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FsBJ7sGYQjtBfJRrqr2tKehbfBt8.png)![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FmBlgK9Ey6-N5Mx1Y8qpTmpBiq9q.png)

---

## 知识小节

> 对于初学框架源码的我来说上面的内容已经有一些压力了。正好个人觉得到此处完成了最基础的 Spring **bean 注入**功能，为了保证接下来学习的顺利，同时也是加深牢固一下理解，理一遍目前的内容，了解其整体流程与写法的深层原因。

### 注册 bean - 非 xml 文件

- DefaultListableBeanFactory 类是目前的核心类，最重要的是实现了 `registryBeanDefinition()`bean 注册、`getBeanDefinition()` 获得 bean 定义信息 操作，其中有字段 `private Map<String, BeanDefinition> beanDefinitionMap` 用于存储所有的 BeanDefinition (即所有注册信息)。值得注意的是：该类可以通过 `getBean()` 获得 bean，但其实该方法是在其祖父类 AbstractBeanFactory 中具体实现的，其只是继承了该类应此可以使用这个方法。
- BeanDefinition 类用来存储单个 bean 的定义信息 ，包括 class、构造参数、属性值等，每个 bean 都拥有一个该实例。此外 PropertyValues 不应该为 null。![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/Fr6Zw3DURueXU_x5m_xkseIXGlSj.png)
- BeanDefinition 类中的各个属性名、属性值信息又单独存储在 PropertyValues 类中，它是 PropertyValue 类 (该类包含 final 修饰的属性名-name、属性值-value，以及必要的双参构造函数与 Getter 函数) 的集合，此外还包含了 `addPropertyValue(PropertyValue propertyValue)`向 PropertyValues 实例中的 `propertyValueList.add()`， `getPropertyValues()` 获得 PropertyValue 数组，`getPropertyValue(String propertyName)` 根据属性名获得对应的 PropertyValue。

很显然的可以看出，我们可以通过向 PropertyValues 类实例中加入 PropertyValue 类实例，然后将 PropertyValue 实例注入到相应的 BeanDefinition 类实例中 (也可以在注入后修改该实例) ，然后调用 `DefaultListableBeanFactory#registryBeanDefinition(String beanName, BeanDefinition beanDefinition�)` 注册 bean，此时 bean 的 Definition 已经存在，但是不使用 bean 就不会实例化 bean。

---

### 实例化 bean

- `DefaultListableBeanFactory#getBean(beanName)`调用`AbstractBeanFactory#getBean(String beanName)`获得 bean 实例，内部调用了 `DefaultSingletonBeanRegistry#getSingleton(String beanName)`获得单例 bean 实例。
- DefaultSingletonBeanRegistry 类，其中有字段`private Map<String, Object> singletonObjects`用于存储所有实例化的 bean，通过`**public **Object getSingleton(String beanName) { **return **singletonObjects.get(beanName); }`**，**返回 Key 为 beanName 的对象。
- `AbstractBeanFactory#getBean(String beanName)`获得对象后判断对象是否存在：
  - 如果`bean != null`，说明已存在该实例，则直接返回该 bean。
  - 反之实例不存在，尝试找到名称为 beanName 的 BeanDefinition，并进行实例化。
    - 调用抽象方法`AbstractBeanFactory#getBeanDefinition(String beanName)`，其在 DefaultListableBeanFactory 类中具体实现，得到 Key == beanName 的 value（BeanDefinition）：
      - `beanDefinition == null`，抛出 bean 未注册的错误。
      - 反之，返回 beanDefiniton。
    - 调用抽象方法 `AbstractBeanFactory#createBean(String beanName, BeanDefinition beanDefinition)`，其在 AbstractAutowireCapableBeanFactory 类中具体实现，调用本类中的 `doCreateBean(String beanName, BeanDefinition beanDefinition)`。
    - AbstractAutowireCapableBeanFactory 类中有字段`**private **InstantiationStrategy instantiationStrategy = 要使用的实例化策略实现类`。该接口有方法`InstantiationStrategy#instantiate(BeanDefinition beanDefinition)`通过不同的是实现类实现不同的实例化策略；`doCreateBean(String beanName, BeanDefinition beanDefinition)` 中调用`AbstractAutowireCapableBeanFactory#createBeanInstance(BeanDefinition beanDefinition){ return getInstantiationStratery().instantiate(beanDefinition) }`返回 bean 实例。
    - 调用 `AbstractAutowireCapableBeanFactory#applyPropertyValues`为 bean 实例填充属性。此外 BeanReference 类用于实现为 bean 填充 bean，该类中记录了 beanName，如果 value 是 BeanReference 类，则说明是填充属性是 bean，那么调用 getBean() 方法直接将 value 变为需要的 bean 实例，目前尚未解决循环依赖的问题。

```java
public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
    try {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            //判断是否有 bean 依赖（instanceof: 判断左右的对象是否为右边类的实例）
            if (value instanceof BeanReference) {
                //beanA 依赖 beanB，需要先实例化 beanB
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }

            //反射注入属性
            BeanUtil.setFieldValue(bean, name, value);
        }
    } catch (Exception e) {
        throw new BeansException("Error setting property values for bean: " + beanName, e);
    }
}
```

      - 调用`DefaultSingletonBeanRegistry#addSingleton(String beanName, Object singletonObject)`方法将实例化的 bean 放入 Map 中，并返回 bean 实例。

---

### 资源 & 资源加载器

> 资源加载是 xml 文件配置生效的前置条件，因此需要先掌握该部分内容

接口 Resource 有方法`InputStream getInputStream() **throws **IOException;`用于获得不同资源的输入流。

- 该接口有三个实现类：ClassPathResource、FileSystemResource、UrlResource，分别代表三种不同的资源，都有一个 private final 的字段用于存储资源的路径，同时都实现接口的方法返回资源输入流。

接口 ResourceLoader 有方法 `Resource getResource(String location);`用于获得 Resource。

- 该接口有实现类 DefaultResourceLoader，实现 getResource() 方法：
  - 判断 location 是否由 classpath: 开头：如果是，返回 `new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));`
  - 如果不是，先尝试作为 url 来处理，如果失败了再尝试作为文件系统下的资源来处理。

---

### 注册 bean - xml 文件

```java
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
```

该接口的抽象实现类 AbstractBeanDefinitionReader 有字段 `**private final **BeanDefinitionRegistry registry;`�`**private **ResourceLoader resourceLoader;`**，**并且字段 BeanDefinitionRegistry 的实例全局共用的，不能重新 new 一个新的，否则会导致存在重名的 bean 或 无法找到已注册的 bean。
由下图可以看出，该抽象实现类并没有实现接口的全部方法，而是指实现了其中一个，并且是遍历 String 数组调用方法。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FiY7x7Mqi9ys8T-bXObltMjLAKp2.png)
类 XmlBeanDefinitionReader 继承了类 AbstractBeanDefinitionReader。

- 首先其构造函数调用父类构造函数 `super(registry);`、`super(registry, resourceLoader);`，初始化 AbstractBeanDefinitionReader 类中的字段。

```java
/**
 * 使用资源加载器获得对应类型的资源
 *
 * @Param: location
 * @ReturnType: void
 * @Author: Cai 🥬
 * @Date: 2022/6/9 11:23
 */
@Override
public void loadBeanDefinitions(String location) throws BeansException {
    ResourceLoader resourceLoader = getResourceLoader();
    Resource resource = resourceLoader.getResource(location);
    loadBeanDefinitions(resource);
}

/**
 * 读取资源配置信息去注册 bean
 *
 * @Param: resource
 * @ReturnType: void
 * @Author: Cai 🥬
 * @Date: 2022/6/9 11:25
 */
@Override
public void loadBeanDefinitions(Resource resource) throws BeansException {
    try {
        InputStream inputStream = resource.getInputStream();
        doLoadBeanDefinitions(inputStream);
    } catch (IOException e) {
        throw new BeansException("IOException parsing XML document from " + resource, e);
    }
}

protected void doLoadBeanDefinitions(InputStream inputStream) {
    //获得 Xml 文件内容流
    Document document = XmlUtil.readXML(inputStream);
    //获得文件根目录
    Element root = document.getDocumentElement();
    //获得根目录下的所有内容
    NodeList childNodes = root.getChildNodes();
    //循环查找、解析 bean 标签
    for (int i = 0; i < childNodes.getLength(); i++) {
        if (childNodes.item(i) instanceof Element) {
            if (BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute(ID_ATTRIBUTE);
                String nameBean = bean.getAttribute(NAME_ATTRIBUTE);
                String className = bean.getAttribute(CLASS_ATTRIBUTE);

                Class<?> clazz = null;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new BeansException("Cannot find class [" + className + "]");
                }
                //id 优先于 name
                String beanName = StrUtil.isNotEmpty(id) ? id : nameBean;
                if (StrUtil.isEmpty(beanName)) {
                    //如果 id 和 name 都为空，将类名第一个字母转为小写作为 bean 的名称
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }

                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                //循环加载当前 bean 的所有定义信息
                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    if (bean.getChildNodes().item(j) instanceof Element) {
                        if (PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())) {
                            //解析 property 标签
                            Element property = (Element) bean.getChildNodes().item(j);
                            String nameProperty = property.getAttribute(NAME_ATTRIBUTE);
                            String valueProperty = property.getAttribute(VALUE_ATTRIBUTE);
                            String refProperty = property.getAttribute(REF_ATTRIBUTE);

                            if (StrUtil.isEmpty(nameProperty)) {
                                throw new BeansException("The property name cannot be null or empty");
                            }

                            Object value = valueProperty;
                            if (StrUtil.isNotEmpty(refProperty)) {
                                value = new BeanReference(refProperty);
                            }
                            PropertyValue propertyValue = new PropertyValue(nameProperty, value);
                            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                        }
                    }
                }

                if (getRegistry().containsBeanDefinition(beanName)) {
                    //bean 名称不可以重复
                    throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                }

                //注册 BeanDefinition
                getRegistry().registryBeanDefinition(beanName, beanDefinition);
            }
        }
    }
}
```

---

## BeanFactoryPostProcessor 和 BeanPostProcessor

> 代码分支：bean-factory-post-processor-and-bean-post-processor

BeanFactoryPostProcessor 和 BeanPostProcessor 是 Spring 框架中极为重要的两个接口，要理解 Spring 的核心原理就必须要理解这两个接口。

### BeanFactoryPostProcessor

**BeanFactoryPostProcessor 是 Spring 提供的容器扩展机制，允许我们在 bean 实例化之前修改 bean 的定义信息（即 BeanDefinition 的信息）**。

- 其重要的实现类有 **PropertyPlaceholderConfigurer** 和 **CustomEditorConfigurer**，PropertyPlaceholderConfigurer 的作用是用 properties 文件的配置值替换 xml 文件中的占位符，CustomEditorConfigurer 的作用是实现类型转换。
- BeanFactoryPostProcessor 的实现原理比较简单。例如：该接口的实现类实现方法 `CustomBeanFactoryPostProcessor#postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory)`，该方法内会通过 beanFactory 以及 beanName 获得对应的 BeanDefinition；然后通过 BeanDefinition 获得 PropertyValues，最后通过添加、修改其内部的 PropertyValue 集合实现属性的添加、修改。

```java
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personDefinition.getPropertyValues();
        //修改属性
        propertyValues.addPropertyValue(new PropertyValue("name", "shi bo"));
    }
}
```

### BeanPostProcessor

BeanPostProcessor 也是 Spring 提供的容器扩展机制，不同的是：**其是在 bean 实例化后修改 bean 或替换 bean**。**BeanPostProcessor 是后面实现 AOP 的关键**。
BeanPostProcessor 的两个方法`postProcessorBeforeInitialization(Object bean, String beanName)`、`postProcessorAfterInitialization(Object bean, String beanName)` 分别**在 bean 执行初始化方法之前和之后执行**。

`AbstractAutowireCapableBeanFactory#doCreateBean(String beanName, BeanDefinition beanDefinition)`方法中有如下几步：

1. 实例化 bean
2. 为 bean 填充属性
3. 初始化 bean，此处`AbstractAutowireCapableBeanFactory#initializeBean(String beanName, Object bean, BeanDefinition beanDefinition)`就是 BeanPostProcessor 作用的位置，具体执行步骤如下：
   1. 执行 BeanPostProcessor 的前置处理：
      1. 遍历字段`AbstractBeanDactory#private final** **List<BeanPostProcessor> beanPostProcessors`的所有 BeanPostProcessor，并执行其 postProcessorBeforeInitialization(Object bean, String beanName) 方法，得到返回的 bean
      2. 如果返回的 bean 不是 null，则代替旧的 bean 并继续遍历；如果返回的 bean 是 null，则返回为执行前置处理的 bean。
   2. 执行 bean 的初始化方法
   3. 执行 BeanPostProcessor 的后置处理，过程同 i 只是调用方法改为 postProcessorAfterInitialization(Object bean, String beanName)
4. 返回 bean 实例

```java
protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
    //执行 BeanPostProcessor 的前置处理
    Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

    //TODO 之后在此处会执行 bean 的初始化方法
    invokeInitMethods(beanName, wrappedBean, beanDefinition);

    //执行 BeanPostProcessor 的后置处理
    wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
    return wrappedBean;
}

@Override
public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    for (BeanPostProcessor processor : getBeanPostProcessors()) {
        Object current = processor.postProcessorBeforeInitialization(result, beanName);
        if (current == null) {
            return result;
        }
        result = current;
    }
    return result;
}

/**
 * 执行 bean 的初始化方法
 *
 * @Param: beanName
 * @Param: bean
 * @Param: beanDefinition
 * @ReturnType: void
 * @Author: Cai 🥬
 * @Date: 2022/6/9 20:27
 */
protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
    //TODO 之后实现
    System.out.println("执行bean[" + beanName + "]的初始化方法");
}

@Override
public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    for (BeanPostProcessor processor : getBeanPostProcessors()) {
        Object current = processor.postProcessorAfterInitialization(result, beanName);
        if (current == null) {
            return result;
        }
        result = current;
    }
    return result;
}
```

---

## 应用上下文 ApplicationContext

代码分支：application-context
应用上下文 ApplicationContext 是 Spring 中比 BeanFactory 更为先进的 IOC 容器，ApplicationContext 除了拥有 BeanFactory 的所有功能外，还支持特殊类型 bean 如上一节中的 BeanFactoryPostProcessor 和 BeanPostProcessor 的自动识别、资源加载、容器事件和监听器、国际化支持、单例 bean 自动初始化等。
**BeanFactory 是 Spring 的基础设施，面向 spring 本身；而 ApplicationContext 面向 spring 的使用者，应用场合使用 ApplicationContext。**
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FlKtDBck_Qd7R_Oxmsv2PzaPY7ab.png)
从 bean 的角度看，目前生命周期如下：
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FlyPiDGzD40UrBfiGVWVuXXiVZ4J.png)

---

## bean 的初始化和销毁方法

> 代码分支：init-and-destroy-method

在 spring 中，定义 bean 的初始化和销毁方法有三种方法：

- 在 xml 文件中制定 init-method 和 destroy-method
- 继承自 InitializingBean 和 DisposableBean
- 在方法上加注解 PostConstruct 和 PreDestroy

第三种通过 BeanPostProcessor 实现，此处不实现，只实现前两种。
针对第一种在 xml 文件中指定初始化和销毁方法的方式，在 BeanDefinition 中增加属性 initMethodName 和 destroyMethodName。
初始化方法在 AbstractAutowireCapableBeanFactory#invokeInitMethods 执行。DefaultSingletonBeanRegistry 中增加属性 disposableBeans 保存拥有销毁方法的 bean，拥有销毁方法的 bean 在 AbstractAutowireCapableBeanFactory#registerDisposableBeanIfNecessary 中注册到 disposableBeans 中。
为了确保销毁方法在虚拟机关闭之前执行，向虚拟机中注册一个钩子方法，查看 AbstractApplicationContext#registerShutdownHook（非 web 应用需要手动调用该方法）。当然也可以手动调用 ApplicationContext#close 方法关闭容器。
到此为止，bean 的生命周期如下：
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FlZFq24TpPJ5ULwfut6nYeNSsZo3.png)

---

## Aware 接口

> 代码分支：aware-interface

Aware 接口是标记性接口，其实现子类能感知容器相关的对象。常用的 Aware 接口有 BeanFactoryAware 和 ApplicationContextAware，分别能让其实现者感知所属的 BeanFactory 和 ApplicationContext。

- 使得实现 BeanFactoryAware 接口的类能感知所属的 BeanFactory，实现比较简单，见 AbstractAutowireCapableBeanFactory#initializeBean 前三行。

```java
if (bean instanceof BeanFactoryAware) {
    ((BeanFactoryAware) bean).setBeanFactory(this);
}
//将 beanFactory 传给该实例
```

- 使得实现 ApplicationContextAware 的接口感知 ApplicationContext，是通过 BeanPostProcessor。由 bean 的生命周期可知，bean 实例化后会经过 BeanPostProcessor 的前置处理和后置处理。定义一个 BeanPostProcessor 的实现类 ApplicationContextAwareProcessor，在 AbstractApplicationContext#refresh 方法中加入到 BeanFactory 中，在前置处理中为 bean 设置所属的 ApplicationContext。

改用 dom4j 解析 xml 文件。
至止，bean 的生命周期如下：
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/Fpz4eWns_71xUqHN5Caj9hJjC22G.png)

---

## bean 作用域（增加 scope=prototype 的支持）

> 代码分支：prototype-bean

每次向容器获取作用域 scope=prototype 的 bean 时，容器都会创建一个新的实例。在 BeanDefinition 中增加描述 bean 的作用域的字段 scope（singleton/prototype），创建作用域 socpe 为 prototype 的 bean 时（AbstractAutowireCapableBeanFactory#doCreateBean），不往 singletonObjects 中增加该 bean。同时 prototype 作用域的 bean 不执行销毁方法，查看 AbstractAutowireCapableBeanFactory#registerDisposableBeanIfNecessary 方法。
至止，bean 的生命周期如下：
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FsFRy1WLZK0455Qt75byymKYENYu.png)

---

## FactoryBean

> 代码分支：factory-bean

FactoryBean 是一种特殊的 bean，当向容器获取该 bean 时，容器不是返回其本身，而是返回其 FactoryBean#getObject 方法的返回值，可通过编码方式定义复杂的 bean。
实现逻辑比较简单，当容器发现 bean 为 FactoryBean 类型时，调用其 getObject 方法返回最终 bean。当 FactoryBean#isSingleton==true，将最终 bean 放进缓存中，下次从缓存中获取。
改动点见 AbstractBeanFactory#getBean。

---

## 容器事件和事件监听器

> 代码分支：event-and-event-listener

ApplicationContext 容器提供了完善的时间发布和时间监听功能。
ApplicationEventMulticaster 接口是注册监听器和发布事件的抽象接口，AbstractApplicationContext 包含其实现类实例作为其属性，使得 ApplicationContext 容器具有注册监听器和发布事件的能力。在 AbstractApplicationContext#refresh 方法中，会实例化 ApplicationEventMulticaster、注册监听器并发布容器刷新事件 ContextRefreshedEvent；在 AbstractApplicationContext#doClose 方法中，发布容器关闭事件 ContextClosedEvent。
![](https://resource-1309599702.cos.ap-shanghai.myqcloud.com/blog/article/images/FmtK5vAQ5mjU1GXSnUk24RTgFFqC.png)

---

---

# AOP

---

## 切点表达式

> 代码分支：pointcut-expression

Joinpoint，织入点，指需要执行代理操作的某个类的某个方法(仅支持方法级别的 JoinPoint)；Pointcut 是 JoinPoint 的表述方式，能捕获 JoinPoint。
最常用的切点表达式是 AspectJ 的切点表达式。

- 匹配类，定义 ClassFilter 接口
- 匹配方法，定义 MethodMatcher 接口

PointCut 需要同时匹配类和方法，包含 ClassFilter 和 MethodMatcher.
demo 中 AspectJExpressionPointcut 是支持 AspectJ 切点表达式的 PointCut 实现，简单实现仅支持 execution 函数。

---

## 基于 JDK 的动态代理

> 代码分支：jdk-dynamic-proxy

AopProxy 是获取代理对象的抽象接口，JdkDynamicAopProxy 的基于 JDK 动态代理的具体实现。TargetSource，被代理对象的封装。MethodInterceptor，方法拦截器，是 AOP Alliance 的"公民"，顾名思义，可以拦截方法，可在被代理执行的方法前后增加代理行为。

---

## 基于 CGLIB 的动态代理

> 代码分支：cglib-dynamic-proxy

基于 CGLIB 的动态代理实现逻辑也比较简单，查看 CglibAopProxy。与基于 JDK 的动态代理在运行期间为接口生成对象的代理对象不同，基于 CGLIB 的动态代理能在运行期间动态构建字节码的 class 文件，为类生成子类，因此被代理类不需要继承自任何接口。

---

## AOP 代理工厂

> 代码分支：proxy-factory

增加 AOP 代理工厂 ProxyFactory，由 AdvisedSupport#proxyTargetClass 属性决定使用 JDK 动态代理还是 CGLIB 动态代理。

---

## 不同的 Advice

> 代码分支：common-advice

一些常用的 Advice：BeforeAdvice / AfterAdvice / AfterReturningAdvice / ThrowsAdvice
Spring 将 AOP 中的 Advice 细化出各种类型的 Advice，常用的有 BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice，我们可以通过扩展 MethodInterceptor 来实现。
实现的过程：定义 MethodBeforeAdviceInterceptor 拦截器，在执行被代理方法之前，先执行 BeforeAdvice 的方法。

---
