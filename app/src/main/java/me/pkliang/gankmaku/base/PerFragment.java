package me.pkliang.gankmaku.base;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Scope @Retention(RUNTIME) public @interface PerFragment {
}
