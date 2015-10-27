module Jekyll
  class SetParameter < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @parameter = text
    end

    def render(context)
      "<%= (request.getParameter(\""<<@parameter<<"\") != null) ? request.getParameter(\""<<@parameter<<"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_parameter', Jekyll::SetParameter)

module Jekyll
  class SetParameterNamed < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @parameter = text
    end

    def render(context)
      "name=\""<<@parameter<<"\"  <%= (request.getParameter(\""<<@parameter<<"\") != null) ? (\"value=\\\"\"+request.getParameter(\""<<@parameter<<"\")+\"\\\"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_parameter_named', Jekyll::SetParameterNamed)

module Jekyll
  class SetParameterWhenAutofill < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @parameter = text
    end

    def render(context)
      "<%= (request.getParameter(\"autoFillUsingParameter\") != null && request.getParameter(\""<<@parameter<<"\") != null) ? request.getParameter(\""<<@parameter<<"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_parameter_when_autofill', Jekyll::SetParameterWhenAutofill)

module Jekyll
  class SetParameterNamedWhenAutofill < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @parameter = text
    end

    def render(context)
      "name=\""<<@parameter<<"\"  <%= (request.getParameter(\"autoFillUsingParameter\") != null && request.getParameter(\""<<@parameter<<"\") != null) ? (\"value=\\\"\"+request.getParameter(\""<<@parameter<<"\")+\"\\\"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_parameter_named_when_autofill', Jekyll::SetParameterNamedWhenAutofill)

module Jekyll
  class SetAttributeWhenAutofill < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @attribute = text
    end

    def render(context)
      "<%= (request.getAttribute(\"autoFillUsingAttribute\") != null && request.getAttribute(\""<<@attribute<<"\") != null) ? request.getAttribute(\""<<@attribute<<"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_attribute_when_autofill', Jekyll::SetAttributeWhenAutofill)

module Jekyll
  class SetAttributeNamedWhenAutofill < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @attribute = text
    end

    def render(context)
      "name=\""<<@attribute<<"\"  <%= (request.getAttribute(\"autoFillUsingAttribute\") != null && request.getAttribute(\""<<@attribute<<"\") != null) ? (\"value=\\\"\"+request.getAttribute(\""<<@attribute<<"\")+\"\\\"\") : \"\" %>"
    end
  end
end
Liquid::Template.register_tag('set_attribute_named_when_autofill', Jekyll::SetAttributeNamedWhenAutofill)

module Jekyll
  class RedirectURLQuery < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      if text == nil or text == ""
        @queryName = "redirect"
      else
        @queryName = text
      end
    end

    def render(context)
      @queryName<<"=<%=Utilities.urlEncodeUTF8(requestBean.getHostRelativeURL())%>"
    end
  end
end
Liquid::Template.register_tag('redirect_url_query', Jekyll::RedirectURLQuery)

