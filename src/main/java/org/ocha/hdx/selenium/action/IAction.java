/**
 * 
 */
package org.ocha.hdx.selenium.action;

import java.util.Map;

/**
 * @author alexandru-m-g
 *
 */
public interface IAction {
	void doAction(Map<String,Object> context);

	default void doAction(final Map<String,Object> context, final String key, final Object value){
		if(key !=null && value!=null){
			context.put(key,value);
		}
		doAction(context);
	}
}
