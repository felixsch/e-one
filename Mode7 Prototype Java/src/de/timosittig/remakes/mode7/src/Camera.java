package de.timosittig.remakes.mode7.src;

public class Camera {
	private float x, y, a;
	private float near, far;
	private float fieldOfViewHalf;

	private static float rotational_speed = 0.5f;
	private static float running_speed = 0.5f;

	public Camera(float x, float y, float a, float near, float far, float fieldOfViewHalf) {
		this.setX(x);
		this.setY(y);
		this.setA(a);

		this.setNear(near);
		this.setFar(far);
		this.setFieldOfViewHalf(fieldOfViewHalf);
	}

	public void update() {
		//if (this.getFar() < 50.0f) this.setFar(this.getFar() + 0.1f);
		//if (this.getNear() < 50.0f) this.setNear(this.getNear() + 0.1f);

		//System.out.println("Far: " + this.getFar() + ", Near: " + this.getNear());
	}

	public float getX() {
		return x;
	}

	private void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	private void setY(float y) {
		this.y = y;
	}

	public float getA() {
		return a;
	}

	private void setA(float a) {
		this.a = a;
	}

	public float getNear() {
		return near;
	}

	public void setNear(float near) {
		this.near = near;
	}

	public float getFar() {
		return far;
	}

	public void setFar(float far) {
		this.far = far;
	}

	public float getFieldOfViewHalf() {
		return fieldOfViewHalf;
	}

	public void setFieldOfViewHalf(float fieldOfViewHalf) {
		this.fieldOfViewHalf = fieldOfViewHalf;
	}

	public void move(int movement) {
		switch (movement) {
		case 1:
			this.moveForeward();
			break;
		case 2:
			this.turnRight();
			break;
		case 3:
			this.moveForeward();
			this.turnRight();
			break;
		case 4:
			this.moveBackward();
			break;
		case 6:
			this.turnRight();
			this.moveBackward();
			break;
		case 8:
			this.turnLeft();
			break;
		case 9:
			this.moveForeward();
			this.turnLeft();
			break;
		case 12:
			this.moveBackward();
			this.turnLeft();
			break;
		default:
			break;
		}
	}

	private void turnLeft() {
		this.setA(this.getA() - (1.0f * rotational_speed));
	}

	private void turnRight() {
		this.setA(this.getA() + (1.0f * rotational_speed));
	}

	private void moveForeward() {
		this.setX(this.getX() + ((float) Math.cos(this.getA()) * 0.2f * running_speed));
		this.setY(this.getY() + ((float) Math.sin(this.getA()) * 0.2f * running_speed));
	}

	private void moveBackward() {
		this.setX(this.getX() - ((float) Math.cos(this.getA()) * 0.2f * running_speed));
		this.setY(this.getY() - ((float) Math.sin(this.getA()) * 0.2f * running_speed));
	}
}