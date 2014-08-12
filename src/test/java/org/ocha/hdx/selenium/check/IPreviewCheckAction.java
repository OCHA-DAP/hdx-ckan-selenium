/**
 * 
 */
package org.ocha.hdx.selenium.check;

import java.util.Map;

import org.ocha.hdx.selenium.util.IFrameWaitHelper;

/**
 * @author alexandru-m-g
 *
 */
public interface IPreviewCheckAction extends ICheckAction, IFrameWaitHelper {
	@Override
	default void doAction(final Map<String,Object> context) {
		this.wrapperDoAction(context);
	}
}
