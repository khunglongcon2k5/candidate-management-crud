CREATE DATABASE CandidateManagement;
GO
USE CandidateManagement;
GO

CREATE TABLE Candidate (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, 
    fullName VARCHAR(50) NOT NULL,
    dateOfBirth VARCHAR(10) NOT NULL,
    [address] VARCHAR(100) NOT NULL,
    hometown VARCHAR(50),
    phoneNumber VARCHAR(11) NOT NULL,
    emailAddress VARCHAR(50) NOT NULL
);
GO    

CREATE TABLE ExperiencedCandidate (
    id INT NOT NULL PRIMARY KEY,  
    yearsOfExperience FLOAT NOT NULL,
    specializedSkills VARCHAR(50) NOT NULL,
    lastWorkplace VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Candidate(id)  
);
GO    

CREATE TABLE FresherCandidate (
    id INT NOT NULL PRIMARY KEY,  
    graduationTime VARCHAR(30) NOT NULL,
    graduationRank VARCHAR(20) NOT NULL,
    graduationSchool VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Candidate(id)  
);
GO

CREATE TABLE InternCandidate (
    id INT NOT NULL PRIMARY KEY, 
    major VARCHAR(30) NOT NULL,
    semester INT NOT NULL,
    schoolName VARCHAR(30) NOT NULL,
    graduationTime VARCHAR(30) NOT NULL,
    FOREIGN KEY (id) REFERENCES Candidate(id) 
);
GO

/*DBCC CHECKIDENT ('Candidate', RESEED, 0)
GO
DELETE FROM CANDIDATE
GO
DELETE FROM EXPERIENCECANDIDATE
GO
DELETE FROM FRESHERCANDIDATE
GO
DELETE FROM INTERNCANDIDATE*/
