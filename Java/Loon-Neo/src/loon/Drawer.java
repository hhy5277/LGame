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
package loon;

import loon.opengl.GLEx;

public abstract class Drawer extends Stage {

	public abstract void paint(GLEx g);

	public abstract void paintFrist(GLEx g);
	
	public abstract void paintLast(GLEx g);
	
	@Override
	public void draw(GLEx g) {
		this.paint(g);
	}

	@Override
	public void drawFrist(GLEx g) {
		this.paintFrist(g);
	}
	
	@Override
	public void drawLast(GLEx g) {
		this.paintLast(g);
	}
}
