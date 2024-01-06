package com.example.designpattern.authenticationScreenInterface;

import java.util.HashMap;
import java.util.Map;

import com.example.designpattern.Default.IAuthentication;

public class IoCContainer {
	private static Map<Class<?>, Class<?>> factories = new HashMap<>();
    private static Map<Class<?>, Boolean> injectionStatus = new HashMap<>();

    public <T> void register(Class<T> inter, Class<? extends T> clazz) {
        factories.put(inter, clazz);
    }

    public static <T> T resolve(Class<T> inter) {
        Class<?> clazz = factories.get(inter);
        if (clazz == null) {
            throw new IllegalArgumentException("No implementation registered for interface " + inter);
        }

        try {
            T instance = inter.cast(clazz.getDeclaredConstructor().newInstance());
            injectDependencies(instance);
            return instance;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating instance for interface " + inter, e);
        }
    }
    
    
    private static <T> void injectDependencies(T instance) {
        Class<?> clazz = instance.getClass();

        if (injectionStatus.getOrDefault(clazz, false)) {
            return;
        }

        injectionStatus.put(clazz, true);

        if (instance instanceof SignInControllerInterface) {
            SignInControllerInterface signInInstance = (SignInControllerInterface) instance;
            SignUpControllerInterface signUpController = resolve(SignUpControllerInterface.class);
            ResetPassword1ControllerInterface resetPassword1Controller = resolve(ResetPassword1ControllerInterface.class);
            signInInstance.setSignUpController(signUpController);
            signInInstance.setResetPassController(resetPassword1Controller);
            signInInstance.setAuthentication(resolve(IAuthentication.class));
        }

        if (instance instanceof SignUpControllerInterface) {
            SignUpControllerInterface signUpInstance = (SignUpControllerInterface) instance;
            SignInControllerInterface signInController = resolve(SignInControllerInterface.class);
            signUpInstance.setSignInController(signInController);
            signUpInstance.setAuthentication(resolve(IAuthentication.class));
        }

        if (instance instanceof ResetPassword1ControllerInterface) {
            ResetPassword1ControllerInterface resetPassword1Instance = (ResetPassword1ControllerInterface) instance;
            SignInControllerInterface signInController = resolve(SignInControllerInterface.class);
            ResetPassword2ControllerInterface resetPassword2Controller = resolve(ResetPassword2ControllerInterface.class);
            resetPassword1Instance.setSignInController(signInController);
            resetPassword1Instance.setResetPassword2Controller(resetPassword2Controller);
            resetPassword1Instance.setAuthentication(resolve(IAuthentication.class));
        }

        if (instance instanceof ResetPassword2ControllerInterface) {
            ResetPassword2ControllerInterface resetPassword2Instance = (ResetPassword2ControllerInterface) instance;
            SignInControllerInterface signInController = resolve(SignInControllerInterface.class);
            resetPassword2Instance.setSignInController(signInController);
            resetPassword2Instance.setAuthentication(resolve(IAuthentication.class));
        }

        injectionStatus.put(clazz, false);
    }
}
