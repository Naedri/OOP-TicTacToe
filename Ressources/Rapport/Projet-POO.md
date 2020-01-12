Cette application respecte et tire profit des fondamentaux de la POO, dont voici quelques exemples :

1. Protection des données
+ Les objets contenus dans la grille sont issus d'une classe d'énumération, limitant ainsi la diversité des données.

2. Favorisation de la réutilisation du code existant
+ La définition d'un package *utilitaires*, et notament les méthodes telles que *getCoordForme()* permet la réutilisation de ce code et une évaluation *a posteriori* des jetons obtenus.

3. Utilisation du polymorphisme
+ La subsomption des méthodes *utilitaires* qui evalue une *CA_Grille* mais qui sont utilisées avec une de ses classes concrètes.
+ La surcharge de certaines méthodes définies dans *CA_Grille_Partie* au sein des classes concrètes directe (TTT et M) et indirectes (MP et TF) a permis la définition spécifique de placement de jeton, d'évaluation de coup et de conditions de fin de match. 

4. Hierachisation des classes, que l'on peut comparer à un arbre :
+ Une interface à la racine
+ Des classes abstraites au niveau des noeuds
+ Des classes concrètes au niveaux des feuilles


