package moe.nova.util;

public final class ClassLoaderHelper {

    private ClassLoaderHelper() {
    }

    private static Class<?> loadClassViaClasses(String fqcn, Class<?>[] classes) {
        if (classes == null) {
            return null;
        }

        for (Class<?> clazz : classes) {
            if (clazz == null) {
                continue;
            }
            ClassLoader loader = clazz.getClassLoader();
            if (loader != null) {
                try {
                    return loader.loadClass(fqcn);
                } catch (ClassNotFoundException e) {
                    // move on to try the next class loader
                }
            }
        }
        return null;
    }

    private static Class<?> loadClassViaContext(String fqcn) {
        ClassLoader loader = contextClassLoader();
        try {
            return loader == null ? null : loader.loadClass(fqcn);
        } catch (ClassNotFoundException e) {
            // Ignored.
        }
        return null;
    }

    /**
     * Loads the class via the optionally specified classes in the order of
     * their specification, and if not found, via the context class loader of
     * the current thread, and if not found, from the caller class loader as the
     * last resort.
     *
     * @param fqcn    fully qualified class name of the target class to be loaded
     * @param classes class loader providers
     * @return the class loaded; never null
     * @throws ClassNotFoundException if failed to load the class
     */
    public static Class<?> loadClass(String fqcn, Class<?>... classes) throws ClassNotFoundException {
        return loadClass(fqcn, true, classes);
    }

    /**
     * If classesFirst is false, loads the class via the context class
     * loader of the current thread, and if not found, via the class loaders of
     * the optionally specified classes in the order of their specification, and
     * if not found, from the caller class loader as the
     * last resort.
     * <p>
     * If classesFirst is true, loads the class via the optionally
     * specified classes in the order of their specification, and if not found,
     * via the context class loader of the current thread, and if not found,
     * from the caller class loader as the last resort.
     *
     * @param fqcn         fully qualified class name of the target class to be loaded
     * @param classesFirst true if the class loaders of the optionally specified classes
     *                     take precedence over the context class loader of the current
     *                     thread; false if the opposite is true.
     * @param classes      class loader providers
     * @return the class loaded; never null
     * @throws ClassNotFoundException if failed to load the class
     */
    public static Class<?> loadClass(String fqcn, boolean classesFirst,
                                     Class<?>... classes) throws ClassNotFoundException {
        Class<?> target;
        if (classesFirst) {
            target = loadClassViaClasses(fqcn, classes);
            if (target == null) {
                target = loadClassViaContext(fqcn);
            }
        } else {
            target = loadClassViaContext(fqcn);
            if (target == null) {
                target = loadClassViaClasses(fqcn, classes);
            }
        }
        return target == null ? Class.forName(fqcn) : target;
    }

    /**
     * Attempt to get the current thread's class loader and fallback to the system classloader if null
     *
     * @return a {@link ClassLoader} or null if none found
     */
    private static ClassLoader contextClassLoader() {
        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        if (threadClassLoader != null) {
            return threadClassLoader;
        }
        return ClassLoader.getSystemClassLoader();
    }

    /**
     * Attempt to get class loader that loads the classes and fallback to the thread context classloader if null.
     *
     * @param classes the classes
     * @return a {@link ClassLoader} or null if none found
     */
    public static ClassLoader classLoader(Class<?>... classes) {
        if (classes != null) {
            for (Class<?> clazz : classes) {
                ClassLoader classLoader = clazz.getClassLoader();

                if (classLoader != null) {
                    return classLoader;
                }
            }
        }

        return contextClassLoader();
    }

}
