package br.gov.sp.fatec.lab5.entity;

public class View {

    public static class UsuarioSimples{}

    public static class UsuarioComercial extends UsuarioSimples {}

    public static class UsuarioCompleto extends UsuarioSimples {}

    public static class ClienteSimples{}

    public static class ClienteCompleto extends ClienteSimples {}

    public static class ClienteSimplesPF extends ClienteSimples {}

    public static class ClienteCompletoPF extends ClienteCompleto {}
}
