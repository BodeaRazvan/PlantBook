-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: plantbook
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(1000) DEFAULT NULL,
  `plant_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkt5qdhy6nfxx7cq52jk731c7m` (`plant_id`),
  KEY `FK24liocg7lhfngonriw16m0usw` (`post_id`),
  KEY `FKfa3htlps9ddix2jx1spmpedko` (`user_id`),
  CONSTRAINT `FK24liocg7lhfngonriw16m0usw` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKfa3htlps9ddix2jx1spmpedko` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkt5qdhy6nfxx7cq52jk731c7m` FOREIGN KEY (`plant_id`) REFERENCES `plant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (23,'https://www.worldatlas.com/r/w1200/upload/89/99/3b/shutterstock-1263201358.jpg',NULL,NULL,1),(24,'https://i.pinimg.com/originals/88/f9/22/88f9220076522307922ed851bcc8c9e4.gif',NULL,1,NULL),(26,'http://techslides.com/demos/sample-videos/small.mp4',NULL,3,NULL),(27,'https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4',NULL,4,NULL),(28,'https://www.youtube.com/embed/tgbNymZ7vqY',NULL,5,NULL),(29,'https://image.shutterstock.com/z/stock-photo-a-picture-of-the-beautiful-view-of-birds-1836263689.jpg',NULL,6,NULL),(32,'https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?fit=476%2C280&ssl=1',NULL,NULL,5),(33,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLK697Psw0vJ3L5uFcb6qodNWkzVKfLaaQOw&usqp=CAU',NULL,9,NULL),(34,'https://cdn.corporate.walmart.com/dims4/WMT/572511c/2147483647/strip/true/crop/1920x1066+0+7/resize/980x544!/quality/90/?url=https%3A%2F%2Fcdn.corporate.walmart.com%2F7b%2F66%2F142c151b4cd3a19c13e1ca65f193%2Fbusinessfornature-banner.png',NULL,10,NULL),(35,'https://hips.hearstapps.com/hmg-prod/images/gettyimages-1205923822-612x612-1597694031.jpg',NULL,NULL,4),(43,'https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg',NULL,18,NULL),(44,'https://media.winnipegfreepress.com/images/1000*1111/ss-Sou-cover-oprion2-Oct28.jpg',NULL,NULL,6),(46,'https://cdn.fbsbx.com/v/t59.2708-21/249757819_1311431495953456_5248742479212498317_n.gif?_nc_cat=107&ccb=1-7&_nc_sid=041f46&_nc_eui2=AeF9VP3A5rA9myGY-dhAMH5GmHy64ZdMO1qYfLrhl0w7WtIr-FmX-q4tZVSlHUayAqOJfLNh2lxmnDuNPXIqu7Qi&_nc_ohc=kmRn7SnEhIcAX_dq0Mc&_nc_ht=cdn.fbsbx.com&oh=03_AVI07o-FjAXIDCDXbZBJU6x1Vb7QWBlE7RBNfRa45onq_w&oe=6289800B',NULL,20,NULL),(56,'https://cdn.shopify.com/s/files/1/0150/6262/products/the-sill_zz-plant_variant_medium_grant_mint.jpg?v=1646670561',NULL,NULL,2),(58,'https://www.graiulsalajului.ro/wp-content/uploads/2021/03/ficus.jpg',3,NULL,NULL),(59,'https://www.floridelux.ro/pub/media/catalog/product/cache/2dc52977e193b03542b973112c860564/f/i/ficus-elastica-belize_1_.jpg',3,NULL,NULL),(60,'https://media.istockphoto.com/photos/flower-rose-petal-blossom-red-nature-beautiful-background-picture-id1091007944?k=20&m=1091007944&s=612x612&w=0&h=1qxztvVpzVbWgrgM9zhBS20_GJeO8iRuFhgz5ygpHXU=',4,NULL,NULL),(61,'https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Small_Red_Rose.JPG/1200px-Small_Red_Rose.JPG',4,NULL,NULL),(62,'https://urbantropicals.com/wp-content/uploads/FIC-TIN-1.jpg',3,NULL,NULL),(63,'https://images.saymedia-content.com/.image/t_share/MTc0MzU1MzIzNzM2Njk2NDU0/how-to-grow-tulips-for-spring-color.jpg',5,NULL,NULL),(64,'https://www.auchan.ro/public/images/haa/ha0/h00/planta-in-ghiveci-cactus-cu-flori-8950012739614.jpg',6,NULL,NULL),(65,'https://www.florariatrias.ro/wp-content/uploads/2020/04/20200407_125234.jpg',6,NULL,NULL),(66,'https://i.natgeofe.com/k/7530d2df-8919-4099-be7f-f5f787a60498/switzerland-matterhorn_4x3.jpg',NULL,21,NULL);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-22  1:46:47
