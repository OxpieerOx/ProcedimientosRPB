
-- Crear la base de datos PROCEDIMIENTOS
CREATE DATABASE PROCEDIMIENTOS;
GO

USE [PROCEDIMIENTOS]
GO
/****** Object:  Table [dbo].[cita]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cita](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idPaciente] [int] NULL,
	[nroCuenta] [varchar](20) NOT NULL,
	[fecha] [date] NOT NULL,
	[horaInicio] [time](7) NOT NULL,
	[horaFin] [time](7) NOT NULL,
	[idProgramacion] [bigint] NOT NULL,
	[estado] [varchar](20) NOT NULL,
	[idMedico] [int] NOT NULL,
	[usuarioCreador] [varchar](50) NOT NULL,
	[fechaRegistro] [datetime] NOT NULL,
	[esAdicional] [bit] NOT NULL,
	[financiamiento] [nvarchar](255) NULL,
	[idProcedimiento] [int] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[empleado]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empleado](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[departamento] [varchar](100) NOT NULL,
	[puesto] [varchar](100) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[telefono] [varchar](15) NULL,
	[email] [varchar](100) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[medico]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medico](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[telefono] [varchar](15) NULL,
	[email] [varchar](100) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[procedimiento]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[procedimiento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[id_servicio] [int] NOT NULL,
	[codigo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[programacion]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[programacion](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[hora_inicio] [time](7) NOT NULL,
	[hora_fin] [time](7) NOT NULL,
	[tiempo_promedio] [bigint] NULL,
	[fecha_registro] [datetime] NOT NULL,
	[usuario_creador] [varchar](255) NOT NULL,
	[id_medico] [int] NOT NULL,
	[id_procedimiento] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rol]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rol](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleServicio]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleServicio](
	[roleId] [int] NOT NULL,
	[servicioId] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[roleId] ASC,
	[servicioId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[servicio]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[servicio](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuario]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK863n1y3x0jalatoir4325ehal] UNIQUE NONCLUSTERED
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsuarioRol]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsuarioRol](
	[userId] [int] NOT NULL,
	[roleId] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[userId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD FOREIGN KEY([idMedico])
REFERENCES [dbo].[medico] ([id])
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD FOREIGN KEY([idProgramacion])
REFERENCES [dbo].[programacion] ([id])
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD  CONSTRAINT [FK_Cita_Procedimiento] FOREIGN KEY([idProcedimiento])
REFERENCES [dbo].[procedimiento] ([id])
GO
ALTER TABLE [dbo].[cita] CHECK CONSTRAINT [FK_Cita_Procedimiento]
GO
ALTER TABLE [dbo].[empleado]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[usuario] ([id])
GO
ALTER TABLE [dbo].[medico]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[usuario] ([id])
GO
ALTER TABLE [dbo].[procedimiento]  WITH CHECK ADD FOREIGN KEY([id_servicio])
REFERENCES [dbo].[servicio] ([id])
GO
ALTER TABLE [dbo].[programacion]  WITH CHECK ADD FOREIGN KEY([id_medico])
REFERENCES [dbo].[medico] ([id])
GO
ALTER TABLE [dbo].[programacion]  WITH CHECK ADD  CONSTRAINT [FK_ProcedimientoProgramacion] FOREIGN KEY([id_procedimiento])
REFERENCES [dbo].[procedimiento] ([id])
GO
ALTER TABLE [dbo].[programacion] CHECK CONSTRAINT [FK_ProcedimientoProgramacion]
GO
ALTER TABLE [dbo].[RoleServicio]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[rol] ([id])
GO
ALTER TABLE [dbo].[RoleServicio]  WITH CHECK ADD FOREIGN KEY([servicioId])
REFERENCES [dbo].[servicio] ([id])
GO
ALTER TABLE [dbo].[UsuarioRol]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[rol] ([id])
GO
ALTER TABLE [dbo].[UsuarioRol]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[usuario] ([id])
GO

-- Insertar un registro en la tabla usuario
INSERT INTO usuario (username, password)
VALUES ('admision', '$2a$12$tx2bPe62LJw4W93yV86zy.i.EF6HzhOiG4O0iskCWM89tVjarMdRK');
GO
