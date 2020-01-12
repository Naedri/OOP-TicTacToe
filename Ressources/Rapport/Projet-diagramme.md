Le diagramme, page suivante présente une organisation ascendante des différentes parties. 

On y observe en amont des interfaces, permettant le lancement des applications par l'*Appli.java*. 
Celles-ci sont implementées par des classes abstraites dont *CA_Grille_Partie* definissant le déroulement commun des parties.
C'est cette dernière que les parties concrétisent.

Ce diagramme a été généré par *ObjectAid UML Explorer* <https://objectaid.com/home>. Les relations entre ses entités étant ajoutées de manière automatique, il est rapidement devenue surchargé et illisible. Afin d'améliorer sa visibilité, les relatiosn entre les packages au profit de celles des classes ont été favorisées. 
Par exemple, comme les classes concrètes du package *partie* utilisent toutes les classes des packages *jetons*, *direction*, il a été résumé ces utilisations par une relation entre *partie* et ceux-ci.

Les messages du package *interaction* ont été crée afin d'aider l'utilisateur. Cependant les consignes du projet contraignant la diversité des messages. Ces sorties ont donc été limitées, mais sont toujours utilisable pour de possibles extensions.



