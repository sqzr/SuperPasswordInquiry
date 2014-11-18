package com.sgk.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil
{
  private Properties tool;

  public PropertiesUtil()
  {
    this.tool = new Properties();
  }

  public boolean loadFile(String filename, String encoding)
  {
    try
    {
      InputStream in = getClass().getResourceAsStream(filename);
      return loadStream(in, encoding);
    } catch (Exception e) {
      e.printStackTrace();
    }return false;
  }

  public boolean loadStream(InputStream stream, String encoding)
  {
    try
    {
      Reader reader = new InputStreamReader(stream, encoding);
      return loadReader(reader);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }return false;
  }

  public synchronized boolean loadReader(Reader reader)
  {
    try
    {
      this.tool.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public synchronized Properties getTool()
  {
    return this.tool;
  }

  public String getString(String key)
  {
    return getTool().getProperty(key);
  }

  public String getString(String key, String defaultValue)
  {
    return getTool().getProperty(key, defaultValue);
  }

  public String[] getStrings(String key, String regex)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    return str.split(regex);
  }

  public Character getCharacter(String key)
  {
    String str = getTool().getProperty(key);
    if ((str == null) || (str.length() != 1)) {
      return null;
    }
    return Character.valueOf(str.charAt(0));
  }

  public Boolean getBoolean(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }

    if (("true".equalsIgnoreCase(str)) || ("1".equals(str))) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }

  public Integer getInteger(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Integer value = null;
    try {
      value = Integer.valueOf(Integer.parseInt(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public Long getLong(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Long value = null;
    try {
      value = Long.valueOf(Long.parseLong(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public Short getShort(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Short value = null;
    try {
      value = Short.valueOf(Short.parseShort(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public Byte getByte(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Byte value = null;
    try {
      value = Byte.valueOf(Byte.parseByte(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public Double getDouble(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Double value = null;
    try {
      value = Double.valueOf(Double.parseDouble(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public Float getFloat(String key)
  {
    String str = getTool().getProperty(key);
    if (str == null) {
      return null;
    }
    Float value = null;
    try {
      value = Float.valueOf(Float.parseFloat(str));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return value;
  }

  public boolean containsKey(String key)
  {
    return getTool().containsKey(key);
  }

  public boolean containsValue(String value)
  {
    return getTool().containsValue(value);
  }

  public Set<Object> getKeySet()
  {
    return getTool().keySet();
  }

  public Set<Map.Entry<Object, Object>> getEntrySet()
  {
    return getTool().entrySet();
  }

  public void setValue(String key, String value)
  {
    getTool().setProperty(key, value);
  }

  public boolean writeToFile(String filename)
  {
    return writeToFile(filename, System.getProperty("file.encoding"));
  }

  public boolean writeToFile(String filename, String encoding)
  {
    File file = new File(filename);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }
    try
    {
      FileOutputStream stream = new FileOutputStream(file);
      return writeToStream(stream, encoding);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }return false;
  }

  public boolean writeToStream(OutputStream stream, String encoding)
  {
    try
    {
      OutputStreamWriter writer = new OutputStreamWriter(stream, encoding);
      return writeToWriter(writer);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }return false;
  }

  public boolean writeToStream(OutputStream stream)
  {
    return writeToStream(stream, System.getProperty("file.encoding"));
  }

  public boolean writeToWriter(Writer writer)
  {
    try
    {
      getTool().store(writer, null);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}