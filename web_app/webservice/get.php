<?php
/**
 * @package AzurWeb/Webservice
 */

/**
 * Partie GET de l'API Webservice
 */
function api_get($var) {
  switch ($var) {
    case ("get_hotels") :
      //Récupération des données
      $tab = array();

      // $tab["hotels"] = array(
      //   array("id"=>1, "nom" => "Hotel 1", "adresse" => "Gaudeloupe - Goyave", "tel" => "0690886012", "cp" => "97122", "adresse"=> "Rue du caca", "rank"=>2.5, "description" => "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed", "prix"=>56.52),
      //   array("id"=>2, "nom" => "Hotel 2", "adresse" => "Gaudeloupe - Capesterre", "tel" => "0690541875", "cp" => "97126", "adresse"=> "Rue de laffont", "rank"=>5.0, "description" => "Une description tres courte 2", "prix"=>152.50),
      //   array("id"=>3, "nom" => "Hotel 3", "adresse" => "Gaudeloupe - Deshaies", "tel" => "0690542896", "cp" => "97118", "adresse"=> "Rue du tfm", "rank"=>4.2, "description" => "Une description tres courte 3", "prix"=>89.50)
      // );
      $tab["hotels"] = array();
      $hotels = getHotel();
      foreach ($hotels as $h) {
        array_push($tab["hotels"], array( "id" => $h['id'], "nom" => $h['nom'], "adresse" => $h['adresse'],
          "tel" => $h['tel'], "cp" => $h['cp'], "rank" => 2.5, "description" => $h['description'], "prix"=> $h['prix'] ));
      }

      print(json_encode($tab));
    break;

    case ("get_users"):
      //Récupération des données sur les utilisateurs
      $tab = array();

      $tab['users'] = array(
        array("mail" => "santos@gmail.com", "mdp" => "mdp971", "nom" => "Dacuna", "prenom" => "Santos"),
        array("mail" => "laffont@gmail.com", "mdp" => "laffont971", "nom" => "Laffont", "prenom" => "Lise")
      );

      print(json_encode($tab));
    break;

    default:
      print("La variable appelée n'existe pas.");
  }
}

 ?>
