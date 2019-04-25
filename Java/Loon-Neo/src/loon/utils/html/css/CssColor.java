/**
 * Copyright 2008 - 2019 The Loon Game Engine Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.5
 */
package loon.utils.html.css;

import loon.canvas.LColor;

public class CssColor extends CssValue {

	public int r;
	public int g;
	public int b;
	public int a;

	public CssColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public void setColor(CssColor color) {
		this.r = color.r;
		this.b = color.b;
		this.g = color.g;
		this.a = color.a;
	}

	public LColor getLColor() {
		return new LColor(r, g, b, a);
	}

	@Override
	public void setKeyword(CssKeyword keyword) {

	}

	@Override
	public void setLength(CssLength length) {

	}

	@Override
	public float toPx() {
		return 0;
	}
}
