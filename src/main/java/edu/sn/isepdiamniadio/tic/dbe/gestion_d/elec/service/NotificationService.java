package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.DemandeCompteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    // Envoi d'un email de confirmation pour une demande de compteur
    public void envoyerConfirmation(DemandeCompteur demandeCompteur) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(demandeCompteur.getClient().getEmail());
            message.setSubject("Confirmation de votre demande de compteur");
            message.setText("Bonjour " + demandeCompteur.getClient() + ",\n\n"
                    + "Votre demande de compteur a été reçue avec succès. Nous vous informerons des prochaines étapes.\n\n"
                    + "Merci,\nVotre équipe de gestion.");
            mailSender.send(message);
            System.out.println("Email de confirmation envoyé à " + demandeCompteur.getClient().getEmail());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }
}
