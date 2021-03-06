USE [SupplierManagement]
GO
/****** Object:  Table [dbo].[Items]    Script Date: 8/24/2021 9:49:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Items](
	[itemCode] [nchar](5) NOT NULL,
	[itemName] [nvarchar](50) NULL,
	[supCode] [nvarchar](5) NULL,
	[unit] [nvarchar](10) NULL,
	[price] [int] NULL,
	[supplying] [bit] NULL,
 CONSTRAINT [PK_Items] PRIMARY KEY CLUSTERED 
(
	[itemCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supliers]    Script Date: 8/24/2021 9:49:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supliers](
	[supCode] [nvarchar](5) NOT NULL,
	[supName] [nvarchar](30) NULL,
	[address] [nvarchar](30) NULL,
	[colloborating] [bit] NULL,
 CONSTRAINT [PK_Supliers] PRIMARY KEY CLUSTERED 
(
	[supCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Items]  WITH CHECK ADD  CONSTRAINT [FK_Items_Suppliers] FOREIGN KEY([supCode])
REFERENCES [dbo].[Supliers] ([supCode])
GO
ALTER TABLE [dbo].[Items] CHECK CONSTRAINT [FK_Items_Suppliers]
GO

INSERT INTO [dbo].[Supliers]
           ([supCode]
           ,[supName]
           ,[address]
           ,[colloborating])
     VALUES
           ('SE100', 'Nguyen Khoi Nguyen', 'HCM', 1),
		   ('SE101', 'Nguyen Trung Dung', 'HCM', 1),
GO

INSERT INTO [dbo].[Items]
           ([itemCode]
           ,[itemName]
           ,[supCode]
           ,[unit]
           ,[price]
           ,[supplying])
     VALUES
           ('H001', 'Laptop', 'SE100', 'cai', 100, 0),
		   ('H002', 'But', 'SE100', 'cai', 100, 0)
GO
