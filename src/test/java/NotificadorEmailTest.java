import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.EmailCliente;
import org.example.NotificadorEmail;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificadorEmailTest {
    @Mock
    private EmailCliente emailClienteMock;

    //Acepa el mensaje y notifica que se envia el correo correctamente
    @Test
    public void testNotificar() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "Hola Mundo");

        verify(emailClienteMock).enviarCorreo("ejemplo@test.com", "Hola Mundo");
    }

    //muestra error al no tener un correo añadido yaq eu es lo principal para agregarlo incluso si esta en comillas
    @Test
    public void testNotificarConDireccionVacia() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("", "Mensaje");

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    //Acepta mensajes vacios con un correo añadido
    @Test
    public void testNotificarConMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", null);

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    //No puede aceptar enviar mensajes ya que no tiene una direccion para mandar
    @Test
    public void testNotificarConDireccionNula() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar(null, "Mensaje");

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    //muestra error al tener un mensaje vacio
    @Test
    public void testNotificarConMensajeVacio() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "");

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    //Muestra error al tener un mensaje Invalido
    @Test
    public void testNotificarConDireccionYMensajeInvalidos() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("", "");

        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }
}
