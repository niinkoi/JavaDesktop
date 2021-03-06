CREATE DATABASE [SE140132_DB]
GO

USE [SE140132_DB]
GO
/****** Object:  Table [dbo].[tbl_Employee]    Script Date: 8/25/2021 3:12:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Employee](
	[Code] [varchar](15) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Address] [varchar](100) NOT NULL,
	[Salary] [int] NOT NULL,
 CONSTRAINT [PK_tbl_Employee] PRIMARY KEY CLUSTERED 
(
	[Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[tbl_Employee]
           ([Code]
           ,[Name]
           ,[Address]
           ,[Salary])
     VALUES
			('E001', 'Nguyen Khoi Nguyen', 'HCM', 100),
		   ('E002', 'Nguyen Thu Ha', 'HCM', 500),
		   ('E003', 'Nguyen Van A', 'HCM', 400),
		   ('E004', 'Nguyen Khoi', 'HCM', 300),
		   ('E005', 'Nguyen Minh', 'HCM', 500)

GO