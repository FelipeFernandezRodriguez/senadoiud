package co.edu.iudigital.senadoiud.services.ifaces;

public interface IEmailService {

    boolean sendMail(String mensaje, String email, String asunto);

}
