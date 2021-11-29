enum EstadoCS{
    SinEstado,Login,Registro,Encriptar,Desencriptar,ObtenerFicheros,SolicitarFichero,EsperarRespuesta,LeerRespuesta,CompartirFicheros,DejarDeCompartir
}
enum EstadoCompartirCS{
    EsperarSolicitud,AtenderSolicitud,MandarRespuesta
}

enum TipoSolicitud{
    Servidor,TuMismo,OtroUsuario
}