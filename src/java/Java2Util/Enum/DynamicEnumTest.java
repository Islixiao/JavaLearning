package Java2Util.Enum;

import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***Created by moyongzhuo
 *On 2018/5/10  ***11:41.
 ******/
public class DynamicEnumTest {
    private static ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
    private static void setFailsafeFieldValue(Field field, Object target, Object value) throws NoSuchFieldException,
            IllegalAccessException {

        // let's make the field accessible
        field.setAccessible(true);
        // next we change the modifier in the Field instance to
        // not be final anymore, thus tricking reflection into
        // letting us modify the static final field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        int modifiers = modifiersField.getInt(field);

        // blank out the final bit in the modifiers int
        modifiers &= ~Modifier.FINAL;
        modifiersField.setInt(field, modifiers);

        FieldAccessor fa = reflectionFactory.newFieldAccessor(field, false);
        fa.set(target, value);
    }
    private static void blankField(Class<?> enumClass, String fieldName) throws NoSuchFieldException,
            IllegalAccessException {
        for (Field field : Class.class.getDeclaredFields()) {
            if (field.getName().contains(fieldName)) {
                AccessibleObject.setAccessible(new Field[] { field }, true);
                setFailsafeFieldValue(field, enumClass, null);
                break;
            }
        }
    }

    private static void cleanEnumCache(Class<?> enumClass) throws NoSuchFieldException, IllegalAccessException {
        blankField(enumClass, "enumConstantDirectory"); // Sun (Oracle?!?) JDK 1.5/6
        blankField(enumClass, "enumConstants"); // IBM JDK
    }
    private static ConstructorAccessor getConstructorAccessor(Class<?> enumClass, Class<?>[] additionalParameterTypes)
            throws NoSuchMethodException {
        Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
        parameterTypes[0] = String.class;
        parameterTypes[1] = int.class;
        System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
        return reflectionFactory.newConstructorAccessor(enumClass.getDeclaredConstructor(parameterTypes));
    }

    private static Object makeEnum(Class<?> enumClass, String value, int ordinal, Class<?>[] additionalTypes,
                                   Object[] additionalValues) throws Exception {
        Object[] parms = new Object[additionalValues.length + 2];
        parms[0] = value;
        parms[1] = Integer.valueOf(ordinal);
        System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);
        return enumClass.cast(getConstructorAccessor(enumClass, additionalTypes).newInstance(parms));
    }

    /**
     * Add an enum instance to the enum class given as argument
     *
     * @param <T> the type of the enum (implicit)
     * @param enumType the class of the enum to be modified
     * @param enumName the name of the new enum instance to be added to the class.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<?>> void addEnum(Class<T> enumType, String enumName,Class<?>[] paramClass,Object[] paramValue) {

        // 0. Sanity checks
        if (!Enum.class.isAssignableFrom(enumType)) {
            throw new RuntimeException("class " + enumType + " is not an instance of Enum");
        }

        // 1. Lookup "$VALUES" holder in enum class and get previous enum instances
        Field valuesField = null;
        Field[] fields = CodeInfoEnum.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().contains("$VALUES")) {
                valuesField = field;
                break;
            }
        }
        AccessibleObject.setAccessible(new Field[] { valuesField }, true);

        try {

            // 2. Copy it
            T[] previousValues = (T[]) valuesField.get(enumType);
            List<T> values = new ArrayList<T>(Arrays.asList(previousValues));

            // 3. build new enum
            T newValue = (T) makeEnum(enumType, // The target enum class
                    enumName, // THE NEW ENUM INSTANCE TO BE DYNAMICALLY ADDED
                    values.size(),
                    //new Class<?>[] {}, // could be used to pass values to the enum constuctor if needed
                    paramClass,
                    //new Object[] {}
                    paramValue
            ); // could be used to pass values to the enum constuctor if needed

            // 4. add new value
            values.add(newValue);
            Object object=values.toArray((T[]) Array.newInstance(enumType, 0));
            // 5. Set new values field
            setFailsafeFieldValue(valuesField, null, object);

            // 6. Clean enum cache
            cleanEnumCache(enumType);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        synchronized (Color.class) {
            addEnum(Color.class,"1",new Class<?>[]{String.class, int.class}, new Object[]{ "ActiveStatus", 2});
        }




        //
        synchronized (CodeInfoEnum.class) {
            addEnum(CodeInfoEnum.class, "3", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{2L, 3L, "ActiveStatus", "Active"});
            addEnum(CodeInfoEnum.class, "4", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{2L, 4L, "ActiveStatus", "Inactive"});
            addEnum(CodeInfoEnum.class, "5", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{3L, 5L, "Optype", "OP1"});
            addEnum(CodeInfoEnum.class, "6", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{3L, 6L, "Optype", "OP2"});
            addEnum(CodeInfoEnum.class, "7", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{3L, 7L, "Optype", "OP3"});
            addEnum(CodeInfoEnum.class, "8", new Class<?>[]{Long.class, Long.class, String.class, String.class}, new Object[]{3L, 8L, "Optype", "OP4"});
        }
        CodeInfoEnum codeInfoEnum =CodeInfoEnum.valueOf("5");
        System.out.println(codeInfoEnum);
        // Run a few tests just to show it works OK.
        System.out.println(Arrays.deepToString(CodeInfoEnum.values()));
        System.out.println("============================打印所有枚举（包括固定的和动态的），可以将数据库中保存的CIC以枚举的形式加载到JVM");
        for(CodeInfoEnum codeInfo:CodeInfoEnum.values()){
            System.out.println(codeInfo.toString());
        }

        System.out.println("============================通过codeId找到的枚举，用于PO转VO的处理");
        CodeInfoEnum activeStatus_Active = CodeInfoEnum.getByInfoId(3L);
        System.out.println(activeStatus_Active);

        System.out.println("============================通过ClassId找到的枚举列表");
        List<CodeInfoEnum> activeStatusEnumList = CodeInfoEnum.getByClassId(3L);
        for(CodeInfoEnum codeInfo : activeStatusEnumList){
            System.out.println(codeInfo);
        }

        System.out.println("============================通过ClassCode和InfoCode获取枚举，用于导入验证CIC合法性");
        CodeInfoEnum toGetActiveStatus_Active = CodeInfoEnum.getByClassCodeAndInfoCode("ActiveStatus","Active");
        System.out.println(toGetActiveStatus_Active);

        System.out.println("============================通过ClassCode和InfoCode获取枚举，输入不存在的Code，则返回NULL");
        CodeInfoEnum toGetActiveStatus_miss = CodeInfoEnum.getByClassCodeAndInfoCode("ActiveStatus","MISS");
        System.out.println(toGetActiveStatus_miss);


    }
}
//我将项目中所有的枚举，都定义在了CodeInfoEnum中，其中包含两部分，一部分是固定的枚举，我是预定义的，比如记录状态（有效|删除），锁定状态（可用|锁定）等，这些的枚举字面值是英文大写单词；另一部分是动态的，具有比较强的业务含义，比如仓库管理中的库位类型，包装类型这些，这些的枚举字面值是数字，即数据库中的ID。
//
//        在使用的使用，注意：
//
//        在和前台交互的时候，统一使用字面值
//        在校验的时候，使用class_code和字面值进行校验。
//        在controller进行参数组装的时候，将前台传入的字面值转成CodeInfoEnum
//        在持久化的时候使用字面值
//        查询的时候，将数据库中的字面值转化为CodeInfoEnum
//        上面写的‘使用’，目前还是设想，还没有动手实现。也是我下面准备做的。希望有看到本文的大神，如能提供宝贵意见，将不胜感激。
//
//        2月10日发布
//        新浪微博微信TwitterFacebook
//        你可能感兴趣的文章
//        Spring Boot 2.0与Java 9 271 浏览
//        Java新兵2017年精华文章 2 收藏，451 浏览
//        解决metrics-spring与springboot1.4不兼容问题 902 浏览
//        评论
//
//
//        文明社会，理性评论
//        Planets
//        想在上方展示你的广告？
//        沈子平 沈子平
//        62 声望
//
//        目录
//        前言


