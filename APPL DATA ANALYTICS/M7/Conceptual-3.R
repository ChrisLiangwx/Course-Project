data = data.frame(x1 = c(1,1,0,5,6,4), x2 = c(4,3,4,1,2,0))
colnames(data) = c('x1', 'x2')
plot(data)

set.seed(1)
data$cluster = sample(2, nrow(data), replace = TRUE) 
print(data)


centroids <- aggregate(cbind(x1, x2) ~ cluster, data = data, mean)
print(centroids)

assign_clusters <- function(observations, centroids) {
  distances <- as.matrix(dist(rbind(observations, centroids[, -1])))
  distances <- distances[1:nrow(observations), (nrow(observations) + 1):nrow(distances)]
  closest <- apply(distances, 1, which.min)
  return(closest)
}

data$cluster <- assign_clusters(data[, 1:2], centroids)
print(data)


#repeat until it doesn't change
repeat {
  previous_centroids <- centroids
  centroids <- aggregate(cbind(x1, x2) ~ cluster, data = data, mean)
  
  data$cluster <- assign_clusters(data[, 1:2], centroids)
  
  if (all(previous_centroids[,  -1] == centroids[, -1])) {
    break
  }
}
print(centroids)
print(data)

#plot
plot(data$x1, data$x2, col = data$cluster, pch = 19, xlab = "X1", ylab = "X2")
points(centroids$x1, centroids$x2, pch = 4, cex = 4, col = 1:2)
legend("topright", legend = c("Cluster 1", "Cluster 2"), col = 1:2, pch = 19)
