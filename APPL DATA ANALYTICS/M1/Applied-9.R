setwd("E:\\Projects\\Course Project\\APPL DATA ANALYTICS\\M1")
Auto = read.table("Auto.data", header=T, na.strings = c("?", " ", "NA"), stringsAsFactors = T)
Auto = na.omit(Auto)
View(Auto)

range(Auto$mpg)
range(Auto$cylinders)
range(Auto$displacement)
range(Auto$horsepower)
range(Auto$weight)
range(Auto$acceleration)

mean(Auto$mpg)
sd(Auto$mpg)
mean(Auto$cylinders)
sd(Auto$cylinders)
mean(Auto$displacement)
sd(Auto$displacement)
mean(Auto$horsepower)
sd(Auto$horsepower)
mean(Auto$weight)
sd(Auto$weight)
mean(Auto$acceleration)
sd(Auto$acceleration)


Auto = Auto[-(10:85),]
range(Auto$mpg)
range(Auto$cylinders)
range(Auto$displacement)
range(Auto$horsepower)
range(Auto$weight)
range(Auto$acceleration)

mean(Auto$mpg)
sd(Auto$mpg)
mean(Auto$cylinders)
sd(Auto$cylinders)
mean(Auto$displacement)
sd(Auto$displacement)
mean(Auto$horsepower)
sd(Auto$horsepower)
mean(Auto$weight)
sd(Auto$weight)
mean(Auto$acceleration)
sd(Auto$acceleration)


par(mfrow = c(1,2))
hist(Auto$year, main = "Histogram of year", xlab = "year")
boxplot(Auto$acceleration ~ Auto$horsepower,
        main = "horsepower versus acceleration",
        xlab = "horsepower",
        ylab = "acceleration")


par(mfrow = c(1,2))
boxplot(Auto$mpg ~ Auto$cylinders,
        main = "cylinders versus mpg",
        xlab = "cylinders",
        ylab = "mpg")
plot(Auto$year,
     Auto$mpg,
     xlab = "Year", 
     ylab = "MPG", 
     main = "MPG over Years")

