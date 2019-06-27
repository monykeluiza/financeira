-- MySQL Script generated by MySQL Workbench
-- 06/03/19 09:47:42
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `financeira` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `financeira` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `financeira` ;

-- -----------------------------------------------------
-- Table `perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `perfil` ;

CREATE TABLE IF NOT EXISTS `perfil` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `data_ultimo_acesso` DATETIME NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  `perfil_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_perfil`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_usuario_perfil_idx` ON `usuario` (`perfil_id` ASC);


-- -----------------------------------------------------
-- Table `meta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meta` ;

CREATE TABLE IF NOT EXISTS `meta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(500) NULL,
  `valor` DECIMAL(13,2) NULL,
  `batida` TINYINT(1) NOT NULL DEFAULT 0,
  `data_alcance` DATE NULL,
  `data_vencimento` DATE NOT NULL,
  `funcionario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_meta_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_meta_funcionario1_idx` ON `meta` (`funcionario_id` ASC);


-- -----------------------------------------------------
-- Table `funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funcionario` ;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `rg` VARCHAR(45) NULL,
  `ctps` VARCHAR(100) NULL,
  `pispasep` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `whatsapp` VARCHAR(45) NULL,
  `endereco` VARCHAR(500) NULL,
  `data_nascimento` DATE NULL,
  `data_inicio` DATE NOT NULL,
  `data_saida` DATE NULL,
  `usuario_id` INT NOT NULL,
  `chefia_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_funcionario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_funcionario1`
    FOREIGN KEY (`chefia_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_funcionario_usuario1_idx` ON `funcionario` (`usuario_id` ASC);

CREATE INDEX `fk_funcionario_funcionario1_idx` ON `funcionario` (`chefia_id` ASC);

-- -----------------------------------------------------
-- Table `cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cliente` ;

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NOT NULL,
  `cpf` VARCHAR(15) NULL,
  `data_nasc` DATE NULL,
  `email` VARCHAR(200) NULL,
  `telefones` VARCHAR(200) NULL,
  `whatsapp` VARCHAR(100) NULL,
  `endereco` VARCHAR(500) NULL,
  `siape` VARCHAR(10) NULL,
  `rg` VARCHAR(45) NULL,
  `orgao` VARCHAR(400) NULL,
  `funcionario_id` INT NOT NULL,
   `beneficio` INT(11) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cliente_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cliente_funcionario1_idx` ON `cliente` (`funcionario_id` ASC);


-- -----------------------------------------------------
-- Table `contato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contato` ;

CREATE TABLE IF NOT EXISTS `contato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `sucesso` TINYINT(1) NOT NULL,
  `obs` VARCHAR(200) NULL,
  `funcionario_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_contato_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contato_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_contato_funcionario1_idx` ON `contato` (`funcionario_id` ASC);

CREATE INDEX `fk_contato_cliente1_idx` ON `contato` (`cliente_id` ASC);


-- -----------------------------------------------------
-- Table `tipo_lembrete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_lembrete` ;

CREATE TABLE IF NOT EXISTS `tipo_lembrete` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lembrete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lembrete` ;

CREATE TABLE IF NOT EXISTS `lembrete` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(500) NOT NULL,
  `tipo_lembrete_id` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `executado` TINYINT(1) NOT NULL DEFAULT 0,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lembrete_tipo_lembrete1`
    FOREIGN KEY (`tipo_lembrete_id`)
    REFERENCES `tipo_lembrete` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lembrete_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_lembrete_tipo_lembrete1_idx` ON `lembrete` (`tipo_lembrete_id` ASC);

CREATE INDEX `fk_lembrete_usuario1_idx` ON `lembrete` (`usuario_id` ASC);


-- -----------------------------------------------------
-- Table `tipo_operacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_operacao` ;

CREATE TABLE IF NOT EXISTS `tipo_operacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco` ;

CREATE TABLE IF NOT EXISTS `banco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(45) NULL,
  `nome_contato` VARCHAR(100) NULL,
  `telefones` VARCHAR(500) NOT NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contrato` ;

CREATE TABLE IF NOT EXISTS `contrato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor_cliente` DECIMAL(13,2) NOT NULL,
  `valor_pago` DECIMAL(13,2) NULL,
  `numero` VARCHAR(200) NOT NULL,
  `valor_contrato` DECIMAL(13,2) NOT NULL,
  `qtd_parcelas` INT(11) NOT NULL,
  `tipo_operacao_id` INT NOT NULL,
  `funcionario_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `data_pgto` DATE NULL,
  `banco_id` INT NOT NULL,
  `data_criacao` DATE not NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_contrato_tipo_operacao1`
    FOREIGN KEY (`tipo_operacao_id`)
    REFERENCES `tipo_operacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_banco1`
    FOREIGN KEY (`banco_id`)
    REFERENCES `banco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_contrato_tipo_operacao1_idx` ON `contrato` (`tipo_operacao_id` ASC);

CREATE INDEX `fk_contrato_funcionario1_idx` ON `contrato` (`funcionario_id` ASC);

CREATE INDEX `fk_contrato_cliente1_idx` ON `contrato` (`cliente_id` ASC);

CREATE INDEX `fk_contrato_banco1_idx` ON `contrato` (`banco_id` ASC);


-- -----------------------------------------------------
-- Table `status_contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status_contrato` ;

CREATE TABLE IF NOT EXISTS `status_contrato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contrato_has_status_contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contrato_has_status_contrato` ;

CREATE TABLE IF NOT EXISTS `contrato_has_status_contrato` (
  `contrato_id` INT NOT NULL,
  `status_contrato_id` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`contrato_id`, `status_contrato_id`),
  CONSTRAINT `fk_contrato_has_status_contrato_contrato1`
    FOREIGN KEY (`contrato_id`)
    REFERENCES `contrato` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_has_status_contrato_status_contrato1`
    FOREIGN KEY (`status_contrato_id`)
    REFERENCES `status_contrato` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_has_status_contrato_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_contrato_has_status_contrato_status_contrato1_idx` ON `contrato_has_status_contrato` (`status_contrato_id` ASC);

CREATE INDEX `fk_contrato_has_status_contrato_contrato1_idx` ON `contrato_has_status_contrato` (`contrato_id` ASC);

CREATE INDEX `fk_contrato_has_status_contrato_usuario1_idx` ON `contrato_has_status_contrato` (`usuario_id` ASC);


-- -----------------------------------------------------
-- Table `tipo_documento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_documento` ;

CREATE TABLE IF NOT EXISTS `tipo_documento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `documento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `documento` ;

CREATE TABLE IF NOT EXISTS `documento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `conteudo` BLOB NOT NULL,
  `cliente_id` INT NOT NULL,
  `tipo_documento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_documento_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_documento_tipo_documento1`
    FOREIGN KEY (`tipo_documento_id`)
    REFERENCES `tipo_documento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_documento_cliente1_idx` ON `documento` (`cliente_id` ASC);

CREATE INDEX `fk_documento_tipo_documento1_idx` ON `documento` (`tipo_documento_id` ASC);


alter table banco add ativo boolean default 1;

DROP TABLE IF EXISTS `log` ;

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `id_tabela` int(11) NOT NULL,
  `acao` varchar(45) NOT NULL,
  `nome_tabela` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

alter table tipo_operacao add ativo boolean default 1;

