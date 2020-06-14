
package Nico;

public class gestionPartie {
    public void disparitionMonstre(int ID){
        //supprimer le monstre avec l'ID de la BDD
    }
    public void enleverVieChateau(int equipe, int ID){
        //récupérer la vie du château
        //récupérer l 'atk du monstre avec l'ID dans la BDD
        //Faire la différence
        disparitionMonstre(ID);
    }
    public void disparitionTour(int ID){
        //supprimer la tour avce l'ID de la BDD
    }
    public void nouveauMonstre(){
        //On met à 0 le compteur de vitesse
        //On iniitialise sa postion selon l'équipe
        //On rajoute le monstre à la liste des monstres de la BDD
    }
    public void actionMonstre(){
        //Pour tout les monstres de la BDD:
            //Si dureeEffet(2)>0
                //On inflige dureeEffet(3) à la vie du monstre
                //dureeEffet(2)--;
            //Si dureeEffet(1)>0
                //On rajoute 0 au compteur de vitesse dans la BDD d'action
                //dureeEffet(1)--;
            //Sinon
                //Si dureeEffet(0)>0
                    //On rajoute 0.6 au compteur de vitesse dans la BDD d'action
                    //dureeEffet(0)--;
                //Sinon
                    ////On rajoute 1 au compteur de vitesse dans la BDD d'action
            //On compare avec le compteur dans la BDD catalogue
            //Si le compteur actif > compteur catalogue
                //On met à 0 le compteur
                //On avance le monstre
            //Si la vie du monstre est négative:
                //disparitionMonstre(ID);
            //Si le monstre a atteint le château
                //enleverVieChateau(equipe,ID);
                //disparitionMonstre(ID);
    }
    public void nouvelleTour(){
        //On met à 0 le compteur de vitesse
        //On rajoute la  tour à la liste des tours de la BDD 
    }
    public void actionTour(){
        //Pour toutes les tours de la BDD
            //Si la vie de la tour est négative:
                //disparitionMonstre(ID);
            // On rajoute 1 au compteur de vitesse dans la BDD d'action
            // On compare le compteur dans la BDD catalogue
            //Si ils sont égaux 
                //On met à 0 le compteur
                //Si le type de la tour est 1 (degats de zone) Tour classique tour précise
                    //On enlève la vie aux n monstres de la BDD de l'équipe adverse
                //Si le type de la tour est 2 (ralentissement)
                    //dureeEffet(0) = 4 pour les n monstres les plus avancés
                //Si le type de la tour est 3 (steun)
                    //dureeEffet(1) = 2 pour les n monstres les plus avancés
                //Si le type de la tour est 4 (dégâts continus)
                    //dureeEffet(2) = 3 pour les n monstres les plus avancés
                    //dureeEffet(3) = 10 pour les n monstres les plus avancés
                
    }
}
