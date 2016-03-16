<?php if ((isset($_GET["action"]))&&(isset($_GET["var"]))) {
  switch ($_GET["action"]) {

    case ("get") :
      api_get($_GET["var"]);
    break;

    case ("post") :
      api_post($_GET["var"]);
    break;

    default: print("L'action appelée n'existe pas.");
  }
} else {
  print("Erreur lors de l'utilisation de l'API");
}

function api_get($var) {
  switch ($var) {
    case ("get_hotels") :
      //Récupération des données
      $tab = array();

      $tab["hotels"] = array(
        array("id"=>1, "nom" => "Hotel 1", "adresse" => "Gaudeloupe - Goyave", "tel" => "0690886012", "cp" => "97122", "adresse"=> "Rue du caca", "rank"=>2.5, "description" => "Une description tres courte", "prix"=>56.52),
        array("id"=>2, "nom" => "Hotel 2", "adresse" => "Gaudeloupe - Capesterre", "tel" => "0690541875", "cp" => "97126", "adresse"=> "Rue de laffont", "rank"=>5.0, "description" => "Une description tres courte 2", "prix"=>152.50),
        array("id"=>3, "nom" => "Hotel 3", "adresse" => "Gaudeloupe - Deshaies", "tel" => "0690542896", "cp" => "97118", "adresse"=> "Rue du tfm", "rank"=>4.2, "description" => "Une description tres courte 3", "prix"=>89.50)
      );

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

function api_post($var){
  // A faire
  switch($var){
    case ("req_login"):
      //
      $get = json_decode(stripslashes($_POST['req_login']));
      // Get data from object
      $mail = $get->mail; // Get name you send
      $mdp = $get->mdp; // Get age of user
      // etc ....

      // Faire la requête sql (Fake query pour le moment)
      $userResult = null;
      $tab = array();

      $tab['users'] = array(
        array("mail" => "santos@gmail.com", "mdp" => "mdp971", "nom" => "Dacuna", "prenom" => "Santos"),
        array("mail" => "laffont@gmail.com", "mdp" => "laffont971", "nom" => "Laffont", "prenom" => "Lise")
      );

      foreach ($tab['users'] as $user) {
        if ($user['mail'] == $mail && $user['mdp'] == $mdp){
          $userResult = $user;
        }
      }

      // On envoie l'utilisateur récuperé en JSON
      print(json_encode($userResult));
    break;

    default:
      print("La variable appelée n'existe pas.");
  }
}
?>
