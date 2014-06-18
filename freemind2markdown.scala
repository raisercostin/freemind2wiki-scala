//source: http://svn.code.sf.net/p/freemind2wiki/code/trunk/freemind2wiki/src/main/java/com/tngtech/freemind2wiki/FM2ConfluenceConverter.java
package org.raisercostin.freemind2wiki
import java.io.BufferedWriter
import java.io.Reader
import java.io.Writer
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.dom4j.Document
import org.dom4j.Element
import org.dom4j.io.SAXReader
import FM2ConfluenceConverter._
import scala.reflect.{ BeanProperty, BooleanBeanProperty }
import scala.collection.JavaConversions._
import scala.reflect.{ BeanProperty, BooleanBeanProperty }
import java.io.Reader
import java.io.Writer
import java.io.FileReader
import java.io.FileWriter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import java.io.BufferedReader
import java.io.IOException
import java.io.Reader
import java.io.Writer
import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import java.util.Map
import java.util.Map.Entry
import java.util.regex.Pattern
import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.dom4j.io.OutputFormat
import org.dom4j.io.XMLWriter
import Confluence2FMConverter._
import scala.reflect.{ BeanProperty, BooleanBeanProperty }
import scala.collection.JavaConversions._
import org.slf4j.LoggerFactory

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Reader
import java.io.Writer
import java.nio.charset.Charset
import java.util.Arrays
import java.util.Properties
import joptsimple.OptionParser
import joptsimple.OptionSet

object NodeIcon extends Enumeration {
  val ATTACH = new NodeIcon("Attach", "attach", null)
  val BACK = new NodeIcon("Back", "back", null)
  val BELL = new NodeIcon("Bell", "bell", null)
  val BOOKMARK = new NodeIcon("Bookmark", "bookmark", "(*)")
  val BROKENLINE = new NodeIcon("Broken Line", "broken-line", null)
  val NO = new NodeIcon("No", "button_cancel", "(x)")
  val YES = new NodeIcon("Yes", "button_ok", "(/)")
  val CALENDAR = new NodeIcon("Calendar", "calendar", null)
  val BOMB = new NodeIcon("Bomb", "clanbomber", null)
  val CLOCK = new NodeIcon("Clock", "clock", null)
  val CLOSED = new NodeIcon("Closed", "closed", null)
  val DECRYPTED = new NodeIcon("Decrypted", "decrypted", null)
  val DESKTOP = new NodeIcon("Desktop", "desktop_new", null)
  val DOWN = new NodeIcon("Down", "down", null)
  val EDIT = new NodeIcon("Edit", "edit", null)
  val ENCRYPTED = new NodeIcon("Encrypted", "encrypted", null)
  val FAMILY = new NodeIcon("Family", "family", null)
  val FEMA = new NodeIcon("FemaleMale", "fema", null)
  val FEMALE1 = new NodeIcon("Female1", "female1", null)
  val FEMALE2 = new NodeIcon("Female2", "female2", null)
  val FLAG = new NodeIcon("Flag", "flag", null)
  val FLAGBLACK = new NodeIcon("Flag-black", "flag-black", null)
  val FLAGBLUE = new NodeIcon("Flag-blue", "flag-blue", null)
  val FLAGGREEN = new NodeIcon("Flag-green", "flag-green", null)
  val FLAGORANGE = new NodeIcon("Flag-orange", "flag-orange", null)
  val FLAGPINK = new NodeIcon("Flag-pink", "flag-pink", null)
  val FLAGYELLOW = new NodeIcon("Flag-yellow", "flag-yellow", null)
  val FOLDER = new NodeIcon("Folder", "folder", null)
  val FORWARD = new NodeIcon("Forward", "forward", null)
  val BUTTERFLY = new NodeIcon("Butterfly", "freemind_butterfly", null)
  val PRIORITY_0 = new NodeIcon("Prio 1", "full-0", null)
  val PRIORITY_1 = new NodeIcon("Prio 1", "full-1", null)
  val PRIORITY_2 = new NodeIcon("Prio 2", "full-2", null)
  val PRIORITY_3 = new NodeIcon("Prio 3", "full-3", null)
  val PRIORITY_4 = new NodeIcon("Prio 4", "full-4", null)
  val PRIORITY_5 = new NodeIcon("Prio 5", "full-5", null)
  val PRIORITY_6 = new NodeIcon("Prio 6", "full-6", null)
  val PRIORITY_7 = new NodeIcon("Prio 7", "full-7", null)
  val PRIORITY_8 = new NodeIcon("Prio 8", "full-8", null)
  val PRIORITY_9 = new NodeIcon("Prio 9", "full-9", null)
  val GO = new NodeIcon("Go", "go", null)
  val HOME = new NodeIcon("Home", "gohome", null)
  val GROUP = new NodeIcon("Group", "group", null)
  val QUESTION = new NodeIcon("Question", "help", "(?)")
  val HOURGLASS = new NodeIcon("Wait", "hourglass", null)
  val IDEA = new NodeIcon("Idea", "idea", "(on)")
  val INFO = new NodeIcon("Info", "info", "(i)")
  val TELEPHONE = new NodeIcon("Telephone", "kaddressbook", null)
  val MAILPROGRAM = new NodeIcon("Mailprogram", "kmail", null)
  val SOUND = new NodeIcon("Sound", "knotify", null)
  val MAILBOX = new NodeIcon("Mailbox", "korn", null)
  val GOOD = new NodeIcon("Good", "ksmiletris", ":)")
  val LAUNCH = new NodeIcon("Launch", "launch", null)
  val ICQ = new NodeIcon("ICQ", "licq", null)
  val LIST = new NodeIcon("List", "list", null)
  val MAIL = new NodeIcon("Mail", "Mail", null)
  val MALE1 = new NodeIcon("Male1", "male1", null)
  val MALE2 = new NodeIcon("Male2", "male2", null)
  val WARNING = new NodeIcon("Warning", "messagebox_warning", "(!)")
  val PASSWORD = new NodeIcon("Password", "password", null)
  val PENCIL = new NodeIcon("Pencil", "pencil", null)
  val PENGUIN = new NodeIcon("Penguin", "penguin", null)
  val PREPARE = new NodeIcon("Prepare", "prepare", null)
  val REDO = new NodeIcon("Redo", "redo", null)
  val SMILEYANGRY = new NodeIcon("SmileyAngry", "smiley_angry", null)
  val SMILEYNEUTRAL = new NodeIcon("SmileyNeutral", "smiley_neutral", null)
  val SMILEYOH = new NodeIcon("SmileyOh", "smiley_oh", null)
  val SMILEYBAD = new NodeIcon("Bad", "smily_bad", ":(")
  val STOP = new NodeIcon("Stop", "stop", null)
  val STOPSIGN = new NodeIcon("StopSign", "stop-sign", null)
  val UP = new NodeIcon("Up", "up", null)
  val WIZARD = new NodeIcon("Wizard", "wizard", null)
  val XMAG = new NodeIcon("Xmag", "xmag", null)
  val EXCLAMATION = new NodeIcon("Exclamation", "yes", null)
  class NodeIcon(@BeanProperty var desc: String, @BeanProperty var fmBuiltin: String, @BeanProperty var confluenceMarkup: String) extends Val
  implicit def convertValue(v: Value): NodeIcon = v.asInstanceOf[NodeIcon]
}
object FM2ConfluenceConverter {
  private var iconMap: Map[String, String] = new HashMap[String, String]()
  val log = LoggerFactory.getLogger(classOf[FM2ConfluenceConverter])
  for (i <- NodeIcon.values) {
    val markup = i.getConfluenceMarkup
    iconMap.put(i.getFmBuiltin, if (markup == null) "::" + i.getDesc + "::" else markup)
  }
}
trait Converter {
  def convert(source: Reader, target: Writer): Unit
}
class FM2ConfluenceConverter extends Converter {
  import FM2ConfluenceConverter._
  @BooleanBeanProperty
  var createOrderedLists: Boolean = false
  @BeanProperty
  var headerNestingDepth: Int = 4
  @BooleanBeanProperty
  var ignoreFontStyle: Boolean = false
  @BooleanBeanProperty
  var ignoreColors: Boolean = false
  @BooleanBeanProperty
  var ignoreIcons: Boolean = false
  @BooleanBeanProperty
  var ignoreLinks: Boolean = false
  override def convert(source: Reader, target: Writer) {
    log.info("convert() - Using FM2ConfluenceConverter")
    var doc: Document = null
    val reader = new SAXReader()
    doc = reader.read(source)
    log.info("convert() - Loaded document " + doc)
    val root = doc.getRootElement
    val nodes = root.selectNodes("node")
    var writer: BufferedWriter = null
    try {
      writer = new BufferedWriter(target)
      writer.append("{toc}\n\n")
      process(nodes, 0, writer)
      writer.append("\n\n{attachments}")
      writer.append("\n")
    } finally {
      if (writer != null) {
        writer.close()
      }
    }
    log.info("convert() - Finished conversion.")
  }
  private def getAttributeValueIgnoreCase(e: Element, attribute: String): String = {
    var v = e.attributeValue(attribute)
    if (v == null || "" == v) {
      v = e.attributeValue(attribute.toLowerCase())
      if (v == null || "" == v) {
        v = e.attributeValue(attribute.toUpperCase())
      }
    }
    if (v == null) "" else v
  }
  private def process(nodes: List[_], depth: Int, target: Writer) {
    for (n <- nodes) {
      if (n.isInstanceOf[Element]) {
        val e = n.asInstanceOf[Element]
        if (depth < getHeaderNestingDepth) {
          target.append("\n")
        }
        if (depth < getHeaderNestingDepth) {
          target.append("h").append("" + (depth + 1)).append(". ")
        } else {
          var i = 0
          while (i <= depth - getHeaderNestingDepth) {
            target.append(if (createOrderedLists) "#" else "*")
            i += 1
          }
          target.append(' ')
        }
        val color = getAttributeValueIgnoreCase(e, "COLOR")
        val link = getAttributeValueIgnoreCase(e, "LINK")
        if (!(ignoreColors || color == null || "" == color)) {
          target.append("{color:").append(color).append("}")
        }
        if (!(ignoreLinks || link == null || "" == link)) {
          target.append("[")
        }
        var bold = false
        var italic = false
        if (!ignoreFontStyle) {
          for (m <- e.selectNodes("font")) {
            if (m.isInstanceOf[Element]) {
              val fontNode = m.asInstanceOf[Element]
              val b = getAttributeValueIgnoreCase(fontNode, "BOLD")
              if (b != null) {
                bold = "true".equalsIgnoreCase(b)
              }
              val i = getAttributeValueIgnoreCase(fontNode, "ITALIC")
              if (i != null) {
                italic = "true".equalsIgnoreCase(i)
              }
            } else {
              log.warn("Expected node to be a font element.")
            }
          }
        }
        val icons = new ArrayList[String]()
        if (!ignoreIcons) {
          for (m <- e.selectNodes("icon")) {
            if (m.isInstanceOf[Element]) {
              val iconNode = m.asInstanceOf[Element]
              val builtinIcon = getAttributeValueIgnoreCase(iconNode, "BUILTIN")
              if (builtinIcon != null) {
                icons.add(builtinIcon)
              } else {
                log.warn("process() - Icon has no BUILTIN attribute")
              }
            } else {
              log.warn("Expected node to be an icon element.")
            }
          }
        }
        if (bold) {
          target.append("*")
        }
        if (italic) {
          target.append("_")
        }
        for (icon <- icons) {
          if (iconMap.containsKey(icon)) {
            target.append(iconMap.get(icon)).append(" ")
          } else {
            target.append("::").append(icon).append(":: ")
          }
        }
        val text = getAttributeValueIgnoreCase(e, "TEXT")
        if (text != null) {
          target.append(text.trim())
        } else {
          log.warn("process() - A node has no text attribute.")
        }
        if (italic) {
          target.append("_")
        }
        if (bold) {
          target.append("*")
        }
        if (!(ignoreLinks || link == null || "" == link)) {
          target.append("|").append(link).append("]")
        }
        if (!(ignoreColors || color == null || "" == color)) {
          target.append("{color}")
        }
        target.append("\n")
        process(e.selectNodes("node"), depth + 1, target)
      } else {
        log.warn("process() - Expected node to be an element.")
      }
    }
  }
}

object Property extends Enumeration {

  val bold = new Property()

  val italic = new Property()

  val color = new Property()

  val link = new Property()

  val icon = new Property()

  val text = new Property()

  class Property extends Val

  implicit def convertValue(v: Value): Property = v.asInstanceOf[Property]
}

object Confluence2FMConverter {
  private val log = LoggerFactory.getLogger(classOf[Confluence2FMConverter])

  private val LIST_ITEM_INDICATOR = Pattern.compile("(\\** )|(#* )")

  private val MACRO_PATTERN = Pattern.compile("\\{\\{.*\\}\\}")

  private val HEADER_INDICATOR = Pattern.compile("h[0123456]\\.")

  private var iconMap: Map[String, String] = new HashMap[String, String]()

  for (i <- NodeIcon.values) {
    val markup = i.getConfluenceMarkup
    if (markup != null) {
      iconMap.put(markup, i.getFmBuiltin)
    }
    iconMap.put("::" + i.getDesc + "::", i.getFmBuiltin)
    iconMap.put("::" + i.getFmBuiltin + "::", i.getFmBuiltin)
  }
}

class Confluence2FMConverter extends Converter {
  import Confluence2FMConverter._
  import Property._

  @BooleanBeanProperty
  var prettyPrint: Boolean = false

  override def convert(source: Reader, target: Writer) {
    log.info("convert() - Using Confluence2FMConverter")
    val document = DocumentHelper.createDocument()
    val map = document.addElement("map")
    val stack = new LinkedList[Element]()
    var headerNestingDepth = 0
    var nodeInserted = false
    var br: BufferedReader = null
    try {
      br = new BufferedReader(source)
      var line: String = null
      var lineCount = 0
      lineCount = 0
      while ({ line = br.readLine(); line != null }) {
        log.debug("Converting: '" + line + "'")
        line = line.trim()
        line = removeIgnoredConfluenceMarkup(line)
        if ("" != line) {
          if (stack.isEmpty) {
            if (line.startsWith("h1.")) {
              val root = map.addElement("node").addAttribute("TEXT", line.substring(3).trim())
              stack.push(root)
              nodeInserted = true
            } else {
              //continue
            }
          } else {
            var depthToReach = -1
            if (line.startsWith("h1.")) {
              log.warn("convert() - Multiple root-level headers 'h1.' detected. All h1-headers except the first one are ignored. ")
            }
            val oldHeaderNestingDepth = headerNestingDepth
            if (line.startsWith("h2.")) {
              depthToReach = 1
              headerNestingDepth = depthToReach
            } else if (line.startsWith("h3.")) {
              depthToReach = 2
              headerNestingDepth = depthToReach
            } else if (line.startsWith("h4.")) {
              depthToReach = 3
              headerNestingDepth = depthToReach
            } else if (line.startsWith("h5.")) {
              depthToReach = 4
              headerNestingDepth = depthToReach
            } else if (line.startsWith("h6.")) {
              depthToReach = 5
              headerNestingDepth = depthToReach
            } else if (lookingAtListItem(line)) {
              for (i <- 1 until line.length) {
                val c = line.charAt(i)
                if (c != '*' && c != '#') {
                  depthToReach = i + headerNestingDepth
                  //break
                }
              }
            }
            if (headerNestingDepth - oldHeaderNestingDepth > 2) {
              depthToReach = oldHeaderNestingDepth + 1
              headerNestingDepth = depthToReach
            }
            if (depthToReach < 0) {
              log.debug("Line skipped")
              //continue
            }
            var i = stack.size
            while (i > depthToReach) {
              stack.pop()
              i -= 1
            }
            line = removeConfluenceNestingMarkup(line)
            line = line.trim()
            val props = new HashMap[Property, Any]()
            determineProperties(line, props)
            val node = stack.peek().addElement("node").addAttribute("TEXT", props.get(Property.text).asInstanceOf[String])
            node.addAttribute("POSITION", "right")
            if (props.containsKey(Property.link)) {
              node.addAttribute("LINK", props.get(Property.link).asInstanceOf[String])
            }
            if (props.containsKey(Property.color)) {
              node.addAttribute("COLOR", props.get(Property.color).asInstanceOf[String])
            }
            if (props.containsKey(Property.icon)) {
              for (icon <- props.get(Property.icon).asInstanceOf[List[_]]) {
                node.addElement("icon").addAttribute("BUILTIN", icon.asInstanceOf[String])
              }
            }
            if (true == props.get(Property.bold) || true == props.get(Property.italic)) {
              val fontNode = node.addElement("font").addAttribute("SIZE", "12")
              if (true == props.get(Property.bold)) {
                fontNode.addAttribute("BOLD", String.valueOf(props.get(Property.bold)))
              }
              if (true == props.get(Property.italic)) {
                fontNode.addAttribute("ITALIC", String.valueOf(props.get(Property.italic)))
              }
            }
            stack.push(node)
          }
        }
        lineCount += 1
      }
    } finally {
      if (br != null) {
        br.close()
      }
    }
    if (!nodeInserted) {
      log.warn("convert() - The confluence markup is either empty or not well-formed and the resulting mind map will be empty.")
    }
    val format = if (prettyPrint) OutputFormat.createPrettyPrint() else OutputFormat.createCompactFormat()
    val writer = new XMLWriter(target, format)
    writer.write(document)
    writer.println()
    writer.close()
    log.info("convert() - Finished conversion.")
  }

  private def lookingAtListItem(line: String): Boolean = {
    LIST_ITEM_INDICATOR.matcher(line).lookingAt()
  }

  private def removeIgnoredConfluenceMarkup(lineP: String): String = {
    var line = lineP
    line = line.replaceAll("----", "")
    line = line.replaceAll("---", "")
    line = line.replaceAll("--", "")
    line = MACRO_PATTERN.matcher(line).replaceAll("")
    line
  }

  private def removeConfluenceNestingMarkup(lineP: String): String = {
    var line = lineP
    line = LIST_ITEM_INDICATOR.matcher(line).replaceFirst("")
    line = HEADER_INDICATOR.matcher(line).replaceFirst("")
    line
  }

  private def determineProperties(lineP: String, props: Map[Property, Any]) {
    var line = lineP
    line = line.trim()
    if (line.startsWith("_") && line.endsWith("_") && line.length > 1) {
      props.put(Property.italic, true)
      line = line.substring(1, line.length - 1)
      determineProperties(line, props)
    } else if (line.startsWith("{_}") && line.endsWith("{_}") && line.length > 1) {
      props.put(Property.italic, true)
      line = line.substring(3, line.length - 3)
      determineProperties(line, props)
    } else if (line.startsWith("*") && line.endsWith("*") && line.length > 1) {
      props.put(Property.bold, true)
      line = line.substring(1, line.length - 1)
      determineProperties(line, props)
    } else if (line.startsWith("{*}") && line.endsWith("{*}") && line.length > 1) {
      props.put(Property.bold, true)
      line = line.substring(3, line.length - 3)
      determineProperties(line, props)
    } else if (line.startsWith("{color:") && line.endsWith("{color}")) {
      val colonOffset = line.indexOf(":")
      if (colonOffset == -1) {
        log.warn("convert() - Strange color tag in line '" + line + "'")
        line = line.substring(7, line.length - 7)
        determineProperties(line, props)
      } else {
        val closeBracketOffset = line.indexOf('}')
        props.put(Property.color, line.substring(7, closeBracketOffset))
        line = line.substring(closeBracketOffset + 1, line.length - 7)
        determineProperties(line, props)
      }
    } else if (line.startsWith("[") && line.endsWith("]")) {
      val pipeOffset = line.indexOf('|')
      val link = (if (pipeOffset == -1) line.substring(1, line.length - 1) else line.substring(pipeOffset + 1, line.length - 1))
      val text = (if (pipeOffset == -1) link else line.substring(1, pipeOffset))
      props.put(Property.link, link)
      props.put(Property.text, text.trim())
      determineProperties(text, props)
    } else if (line.startsWith("::")) {
      val endDoubleColonOffset = line.indexOf("::", 2)
      if (endDoubleColonOffset != -1) {
        if (!props.containsKey(Property.icon)) {
          props.put(Property.icon, new ArrayList())
        }
        val iconDeclaration = line.substring(2, endDoubleColonOffset)
        val builtin = iconMap.get("::" + iconDeclaration + "::")
        if (builtin == null) {
          props.get(Property.icon).asInstanceOf[List[String]].add(iconDeclaration)
        } else {
          props.get(Property.icon).asInstanceOf[List[String]].add(builtin)
        }
        line = line.substring(endDoubleColonOffset + 2)
        determineProperties(line, props)
      } else {
        props.put(Property.text, line.trim())
      }
    } else {
      for ((key, value) <- iconMap) {
        val confluenceIconMarkup = key
        if (line.startsWith(key)) {
          val builtin = value
          if (!props.containsKey(Property.icon)) {
            props.put(Property.icon, new ArrayList())
          }
          props.get(Property.icon).asInstanceOf[List[String]].add(builtin)
          line = line.substring(key.length)
          determineProperties(line, props)
          //break
        }
      }
      props.put(Property.text, line.trim())
    }
  }
}

object FreeMindWikiConverter {
  private val log = LoggerFactory.getLogger(FreeMindWikiConverter.getClass)

  def main(args: Array[String]) {
    val defaultCharset = Charset.defaultCharset().name()
    try {
      val parser = new OptionParser()
      parser.acceptsAll(Arrays.asList("c", "convert"), "Specifies the type of conversion to perform. Valid arguments " + "are 'freemind2confluence' and 'confluence2freemind'.").withRequiredArg().describedAs("conversion")
      parser.acceptsAll(Arrays.asList("s", "source"), "The source file. If not specified the converter will read " + "from standard input.").withRequiredArg().describedAs("file")
      parser.acceptsAll(Arrays.asList("t", "target"), "The destination file. If not specified the converter will " + "write to standard output.").withRequiredArg().describedAs("file")
      parser.accepts("source-charset", "The encoding of the converter input: " + "e.g. 'UTF-8', 'US-ASCII', 'ISO-8859-15'. " + "Your system default is: " + defaultCharset).withRequiredArg().describedAs("charset")
      parser.accepts("target-charset", "The encoding of the converter output: " + "e.g. 'UTF-8', 'US-ASCII', 'ISO-8859-15'. " + "Your system default is: " + defaultCharset).withRequiredArg().describedAs("charset")
      val optionSet = parser.parse(args)
      if (!optionSet.hasArgument("c")) {
        System.err.println("No conversion mode specified.")
        System.err.println()
        parser.printHelpOn(System.err)
        System.exit(-1)
      }
      var converter: Converter = null
      var sourceCharset = defaultCharset
      var targetCharset = defaultCharset
      val configuration = new Properties()
      if (!optionSet.has("source-charset") || !optionSet.has("target-charset")) {
        tryToLoadConfiguration(configuration)
      }
      val conversion = optionSet.argumentOf("c")
      if ("freemind2confluence" == conversion) {
        converter = new FM2ConfluenceConverter()
        sourceCharset = configuration.getProperty("freemind.sourceCharset", sourceCharset)
        targetCharset = configuration.getProperty("confluence.targetCharset", targetCharset)
      } else if ("confluence2freemind" == conversion) {
        converter = new Confluence2FMConverter()
        sourceCharset = configuration.getProperty("confluence.sourceCharset", sourceCharset)
        targetCharset = configuration.getProperty("freemind.targetCharset", targetCharset)
      } else {
        System.err.println("Unsupported conversion: " + conversion)
        System.err.println()
        parser.printHelpOn(System.err)
        System.exit(-1)
        return
      }
      if (optionSet.has("source-charset")) {
        sourceCharset = optionSet.argumentOf("source-charset")
      }
      if (optionSet.has("target-charset")) {
        targetCharset = optionSet.argumentOf("target-charset")
      }
      var r: Reader = null
      var w: Writer = null
      log.info("Using source charset: " + sourceCharset)
      log.info("Using target charset: " + targetCharset)
      r = if (optionSet.hasArgument("s")) new InputStreamReader(new FileInputStream(optionSet.valueOf("s").asInstanceOf[String]), sourceCharset) else new InputStreamReader(System.in, sourceCharset)
      w = if (optionSet.hasArgument("t")) new OutputStreamWriter(new FileOutputStream(optionSet.valueOf("t").asInstanceOf[String]), targetCharset) else new OutputStreamWriter(System.out, targetCharset)
      converter.convert(r, w)
    } catch {
      case e: Throwable => {
        e.printStackTrace()
        System.exit(-1)
      }
    }
  }

  private def tryToLoadConfiguration(properties: Properties) {
    //    val confPath = "/freemind2wiki.conf"
    //    val confIn = classOf[FreeMindWikiConverter].getResourceAsStream(confPath)
    //    if (confIn != null) {
    //      try {
    //        properties.load(confIn)
    //      } catch {
    //        case e: Exception => log.error(String.format("Could not load configuration file '%s'.", confPath))
    //      }
    //    } else {
    //      log.warn(String.format("Configuration file '%s' not in classpath.", confPath))
    log.warn("Configuration file '%s' not in classpath.")
    //    }
  }
}

@RunWith(classOf[JUnitRunner])
class ProductsScraperTest extends FunSuite {
  //import io.Locations

  test("convert1") {
    val folder = """d:\personal\work\raisercostin-utils\test\"""
    val c = new FM2ConfluenceConverter
    c.convert(new FileReader(folder + """darzar.mm"""), new FileWriter(folder + """darzar.md"""))
  }
}