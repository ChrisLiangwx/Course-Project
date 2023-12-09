setwd("E:\\Projects\\Course Project\\APPL DATA ANALYTICS\\M1")
college = read.csv("College.csv")
rownames(college) = college[ , 1]
college = college[ , -1]
View(college)

summary(college)

pairs(college[,2:10])

boxplot(college$Outstate ~ college$Private,
        main = "Outstate versus Private",
        xlab = "Private",
        ylab = "Outstate")


Elite = rep("No", nrow(college))
Elite[college$Top10perc > 50] = "Yes"
Elite = as.factor(Elite)
college = data.frame(college, Elite)
summary(college$Elite)
boxplot(college$Outstate ~ college$Elite,
        main = "Outstate vs Elite",
        xlab = "Elite",
        ylab = "Outstate")


par(mfrow = c(2,2))
hist(college$Top10perc, main = "Histogram of Top10perc", xlab = "Top10perc")
hist(college$Top25perc, main = "Histogram of Top25perc", xlab = "Top25perc")
hist(college$Grad.Rate, main = "Histogram of Grad.Rate", xlab = "Grad.Rate")
hist(college$Enroll, main = "Histogram of Enroll", xlab = "Enroll")


boxplot(college$Grad.Rate ~ college$Top10perc,
        main = "Top10perc versus Grad.Rate",
        xlab = "Top10perc",
        ylab = "Grad.Rate")
