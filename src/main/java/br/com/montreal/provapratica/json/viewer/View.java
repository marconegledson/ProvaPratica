package br.com.montreal.provapratica.json.viewer;

public interface View {
	interface Summary {};
	interface Father extends Summary {};
	interface Children extends Summary {};
}
