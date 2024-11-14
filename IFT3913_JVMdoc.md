# Tâche 3

## Changements apportés

Pour rendre la GitHub Action compatible avec plusieurs flags JVM, nous avons utilisé `matrix.jvm_flags` dans le fichier de configuration. Cela permet d'exécuter le pipeline Maven avec une série de paramètres différents pour observer leurs effets. Voici un résumé des changements principaux :

**1. Ajout de matrix.jvm_flags** : Nous avons intégré une matrice de cinq flags JVM (-XX:+UseG1GC, -XX:+UnlockDiagnosticVMOptions, -XX:+PrintGCDetails, -XX:+PrintCompilation, -XX:+HeapDumpOnOutOfMemoryError) dans la configuration de l’action. Ce choix permet d'exécuter des builds parallèles avec chaque flag, optimisant les ressources et accélérant la collecte de données comparatives sur les effets spécifiques de chaque flag. Cela améliore l'efficacité des tests et fournit des insights diversifiés pour chaque build.

**2. Modification de MAVEN_OPTS** : Pour chaque itération de build, le flag correspondant est assigné dynamiquement à la variable d'environnement MAVEN_OPTS, configurant Maven pour utiliser la configuration JVM spécifique de la matrice. Cette approche garantit que chaque build Maven bénéficie d’une configuration JVM adaptée, ce qui permet de mesurer avec précision les effets de chaque flag sur la couverture de code, la stabilité et les performances.

**3. Nouvelle étape de logging** : Un log spécifique a été ajouté pour documenter le flag JVM utilisé dans chaque itération et afficher le taux de couverture de code obtenu avec ce flag. Ce log est essentiel pour suivre l’impact des paramètres JVM en temps réel et permet d’identifier facilement les configurations les plus performantes. Ces logs facilitent également le diagnostic en cas de dysfonctionnements ou de dégradations de performance associées à un flag spécifique.


## Choix des flags JVM et justification

Les flags sélectionnés visent à améliorer les performances et la visibilité du comportement de la JVM pendant l'exécution du code. Voici une explication des raisons de leur sélection :

1. **`-XX:+UseG1GC`**
   
**• Qualité** : Le garbage collector G1 aide à maintenir la stabilité du système en assurant un nettoyage de la mémoire plus prévisible et en minimisant les interruptions. Cette stabilité est essentielle pour des tests continus et fiables, car elle réduit les risques d’erreurs inattendues dues à des pauses prolongées dans la gestion de la mémoire.

**• Performance** : Ce flag est optimisé pour les applications avec des charges de travail fluctuantes et des exigences de réactivité. En divisant la gestion de la mémoire en petites régions et en ciblant celles qui ont le plus de « débris », G1 réduit le temps passé en pause par rapport aux autres garbage collectors.

**• Observabilité** : G1 fournit des données précieuses sur la fréquence et la durée des cycles de garbage collection, ce qui peut être crucial pour surveiller la santé de l’application en temps réel et ajuster les paramètres si des inefficacités sont détectées.

2. **`-XX:+UnlockDiagnosticVMOptions`**  

**• Qualité** : En activant des options de diagnostic supplémentaires, ce flag améliore les possibilités de débogage, permettant d'identifier plus rapidement les problèmes de qualité tels que les fuites de mémoire et les erreurs de logique dans le code.

**• Performance** : Bien que ce flag ne vise pas directement la performance, il permet d'accéder à d’autres flags qui peuvent être utilisés pour des tests de performance avancés, optimisant ainsi les tests et réglages futurs.

**• Observabilité** : Ce flag augmente considérablement l’observabilité en permettant l’activation d’options de diagnostic supplémentaires pour une vue plus approfondie des processus internes de la JVM, ce qui est précieux pour l’analyse des performances et de la qualité.

3. **`-XX:+PrintGCDetails`**  

**• Qualité** : En détaillant les opérations de garbage collection, ce flag aide à identifier les zones de code qui génèrent une utilisation excessive de mémoire ou de collectes inutiles. Cela permet d’affiner le code pour une meilleure efficacité mémoire, évitant ainsi des problèmes de surcharge ou d’épuisement de la mémoire.

**• Performance** : En analysant les logs détaillés, on peut ajuster les paramètres de la JVM pour réduire les cycles de garbage collection qui pourraient affecter la fluidité de l’application, assurant ainsi des performances plus constantes.

**• Observabilité** : Ce flag améliore la visibilité sur les processus de garbage collection en affichant des détails, tels que le temps passé dans chaque phase et l'espace récupéré, permettant une compréhension approfondie de la gestion de la mémoire dans le contexte spécifique de l'application.

4. **`-XX:+PrintCompilation`**  

**• Qualité** : En affichant chaque méthode compilée, ce flag aide à détecter des méthodes qui peuvent bénéficier d’optimisations. Par exemple, les méthodes fréquemment réutilisées et qui nécessitent beaucoup de ressources peuvent être ciblées pour des optimisations, renforçant la qualité et l'efficacité du code.

**• Performance** : Ce flag permet de détecter des ralentissements dus à la compilation JIT (Just-In-Time) en temps réel. En étudiant ces informations, il est possible de réduire l’impact de la compilation dynamique sur l’exécution en optimisant la configuration de la JVM pour certaines méthodes critiques.

**• Observabilité** : Ce flag fournit une vue continue des compilations JIT en cours, ce qui est utile pour comprendre le flux d’exécution et optimiser les méthodes qui ralentissent le système, particulièrement dans les applications où les performances en temps réel sont essentielles.

5. **`-XX:+HeapDumpOnOutOfMemoryError`**  

**• Qualité** : Ce flag est essentiel pour diagnostiquer les erreurs critiques liées à la mémoire, telles que les fuites. Lorsqu’un OutOfMemoryError se produit, le dump de la mémoire offre un instantané détaillé de l’état du système, permettant d’identifier les objets ou processus responsables et de corriger les erreurs pour améliorer la fiabilité de l’application.

**• Performance** : En identifiant les causes profondes des erreurs de mémoire, ce flag aide à éviter les crashs futurs, ce qui assure des performances stables à long terme. Bien que ce flag soit plus réactif, il fournit des données qui, lorsqu'elles sont analysées, mènent à des optimisations.

**• Observabilité** : Ce flag augmente la capacité de diagnostic post-mortem en offrant des informations précises sur l'état de la mémoire lors d'une panne. En analysant le dump, il devient plus facile de suivre les causes et de prendre des mesures proactives pour améliorer la gestion de la mémoire.

## Humour

Concernant **`-XX:+UnlockDiagnosticVMOptions`** : Comme une porte secrète dans un jeu vidéo, cette option ouvre des fonctionnalités cachées... mais parfois, mieux vaut ne pas trop explorer; le boss qu'on affronterait pourrait nous prendre des jours à faire tomber (et beaucoup de potions de soin, ou en d'autre terme, beaucoup de café) ! Au moins prenez avec vous un soigneur (le stagiaire), vous pourrez alors vous attribuer la gloire du coup fatal alors qu'il supportait vos blessures (et détresses mentales) tout le long...
