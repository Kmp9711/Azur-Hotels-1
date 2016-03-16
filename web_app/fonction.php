<?php

define("IN_WAIT", 1);
define("CONFIRM", 2);
define("VALID", 3);
define("CANCEL", 4);

//Permet de tester la connexion
function tryLogin ($mail,$mdp)
{
	global $bdd;
	$req=$bdd->prepare("SELECT * FROM user Where mail = :mail AND mdp =:mdp");
	$req->bindValue(":mail", $mail,PDO::PARAM_STR);
	$req->bindValue(":mdp", $mdp,PDO::PARAM_STR);
	$req->execute();
	$user = $req->fetch();
    return $user;

}

//Permet de s'inscrire
function signUp($nom,$prenom,$tel,$adress,$mail,$mdp)
{
    global $bdd;
	$req1=$bdd->prepare("SELECT * FROM user Where mail = :mail");
	$req1->bindValue(":mail",$mail,PDO::PARAM_STR);
    $req1->execute();
    $userexist = $req1->rowCount();	
    if($userexist == 0)
    {

		$req=$bdd->prepare("INSERT INTO user(nom,prenom,tel,adresse,mail,mdp) VALUES(:nom,:prenom,:tel,:adresse,:mail,:mdp)");
		$req->bindValue(":nom",$nom,PDO::PARAM_STR);
		$req->bindValue(":prenom",$prenom,PDO::PARAM_STR);
		$req->bindValue(":tel",$tel,PDO::PARAM_INT);
		$req->bindValue(":adresse",$adress,PDO::PARAM_STR);
		$req->bindValue(":mail",$mail,PDO::PARAM_STR);
		$req->bindValue(":mdp",$mdp,PDO::PARAM_STR);
		$req->execute();
    }

    else{

    	echo" requete  null";
    }


}

//Permet d'afficher les hotels
function getHotel()
{
	 global $bdd;
	$req=$bdd->prepare("SELECT * FROM Hotel");
    $req->execute();
    $res = $req->fetchAll();
    return $res;

}

function getHotelnoReserv()
{
	 global $bdd;
	$req=$bdd->prepare("SELECT * FROM Hotel WHERE id NOT IN (SELECT id_hotel FROM reservation)");
    $req->execute();
    $res = $req->fetchAll();
    return $res;

}



//Permet d'ajouter une reservation
function addReservation($dateDebut,$dateFin,$iduser,$idhotel)
{

    global $bdd;
	$req = $bdd->prepare("INSERT INTO  reservation(datedebut,datefin,id_user,id_hotel) VALUES(:datedebut,:datefin,:id_user,:id_hotel)");
	$req->bindValue(":datedebut", $dateDebut,PDO::PARAM_STR);
	$req->bindValue(":datefin",$dateFin,PDO::PARAM_STR);
	$req->bindValue(":id_user",$iduser,PDO::PARAM_INT);
    $req->bindValue(":id_hotel",$idhotel,PDO::PARAM_INT);
	$insert = $req->execute();

	if($insert)
	{
		return true;   
	}

	else
	{
		return false;
	}
   	 

}

//Permet de'annuler une reservation
function cancelReservation($idReservation,$etat){

    global $bdd;
	$req1 = $bdd->prepare("SELECT etat FROM Reservation Where id = :id");
    $bdd->bindValue(":id",$idReservation,PDO::PARAM_INT);
	$res = $req1->fetch();
	$etat = $res['etat'];

	if($etat == IN_WAIT || $etat == CONFIRM)
    {

		  $req = $bdd->prepare("UPDATE Reservation SET etat = :etat Where id = :id ");
		  $bdd->bindValue(":etat",CANCEL,PDO::PARAM_INT);
		  $bdd->bindValue(":id",$idReservation,PDO::PARAM_INT);
		  $req->execute();
 
  	}
  
    return $etat;
}


?>