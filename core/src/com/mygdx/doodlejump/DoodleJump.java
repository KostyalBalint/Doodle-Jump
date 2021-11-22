package com.mygdx.doodlejump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

public class DoodleJump extends ApplicationAdapter {
	private OrthographicCamera camera;

	private Doodler doodler;

	private List<Platform> platforms;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render() {
		//Clear the screen
		ScreenUtils.clear(0, 0, 0, 1);

		//Update the camera
		camera.update();

		//Draw all the platforms
		platforms.forEach(platform -> platform.draw(camera));

		//Draw the black hole if any

		//Draw the doodler

		//Process user input
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			//Move the doodler left
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			//Move the doodler right
		}

		//Update camera position
		//Camera is based on the highest level, that the doodler jumped on

		//Handle doodler auto-jump

	}

	@Override
	public void dispose() {
		//Dispose all the resources
	}
}
