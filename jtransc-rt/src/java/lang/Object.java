/*
 * Copyright 2016 Carlos Ballesteros Velasco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import com.jtransc.annotation.JTranscKeep;
import com.jtransc.annotation.haxe.*;

import java.io.IOException;
import java.lang.reflect.Field;

@HaxeAddMembers({
	"static public var __LAST_ID__ = 0;",
	"public var __ID__ = __LAST_ID__++;",
})
@HaxeAddFilesTemplate({
	"N.hx",
	//"hx/N.hx",
	"R.hx",
	"HaxeNatives.hx",
	"HaxeDynamicLoad.hx",
	"HaxeFfiLibrary.hx",
	"HaxeIO.hx",
	"HaxeFormat.hx",
	"HaxeNativeWrapper.hx",
	"HaxeArrayBase.hx",
	"HaxeArrayBool.hx",
	"HaxeArrayByte.hx",
	"HaxeArrayShort.hx",
	"HaxeArrayChar.hx",
	"HaxeArrayInt.hx",
	"HaxeArrayFloat.hx",
	"HaxeArrayDouble.hx",
	"HaxeArrayLong.hx",
	"HaxeArrayAny.hx"
})
@HaxeAddSubtarget(name = "js", alias = {"default", "javascript"}, cmdSwitch = "-js", singleFile = true, interpreter = "node", extension = "js")
@HaxeAddSubtarget(name = "cpp", alias = {"c", "c++"}, cmdSwitch = "-cpp", singleFile = true, interpreter = "", extension = "exe")
@HaxeAddSubtarget(name = "swf", alias = {"flash", "as3"}, cmdSwitch = "-swf", singleFile = true, interpreter = "", extension = "swf")
@HaxeAddSubtarget(name = "neko", cmdSwitch = "-neko", singleFile = true, interpreter = "neko", extension = "n")
@HaxeAddSubtarget(name = "php", cmdSwitch = "-php", singleFile = false, interpreter = "neko", extension = "php", interpreterSuffix = "/index.php")
@HaxeAddSubtarget(name = "cs", cmdSwitch = "-cs", singleFile = true, interpreter = "", extension = "exe")
@HaxeAddSubtarget(name = "java", cmdSwitch = "-java", singleFile = true, interpreter = "java -jar", extension = "jar")
@HaxeAddSubtarget(name = "python", cmdSwitch = "-python", singleFile = true, interpreter = "python", extension = "py")
public class Object {
	@JTranscKeep
	public boolean equals(Object obj) {
		return (this == obj);
	}

	@HaxeMethodBody("return HaxeNatives.getClass(this);")
	native public final Class<?> getClass();

	@JTranscKeep
	public int hashCode() {
		return System.identityHashCode(this);
	}

	@JTranscKeep
	protected Object clone() throws CloneNotSupportedException {
		// @TODO: This is slow! We could override this at code gen knowing all the fields and with generated code to generate them.
		try {
			Class<?> clazz = this.getClass();
			Object newObject = clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				field.set(newObject, field.get(this));
			}
			return newObject;
		} catch (Throwable e) {
			throw new CloneNotSupportedException(e.toString());
		}
	}

	@JTranscKeep
	public String toString() {
		Class<?> clazz = getClass();
		String hashCode = Integer.toHexString(this.hashCode());
		if (clazz != null) {
			return clazz.getName() + "@" + hashCode;
		} else {
			return "null@" + hashCode;
		}
	}

	public final void notify() {
	}

	public final void notifyAll() {
	}

	public final void wait(long timeout) {
	}

	public final void wait(long timeout, int nanos) {
	}

	public final void wait() {
	}

	protected void finalize() throws IOException {
	}
}
